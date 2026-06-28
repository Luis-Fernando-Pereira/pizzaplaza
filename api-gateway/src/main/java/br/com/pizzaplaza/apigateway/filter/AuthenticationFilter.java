package br.com.pizzaplaza.apigateway.filter;

import br.com.pizzaplaza.apigateway.annotation.PublicEndpoint;
import br.com.pizzaplaza.apigateway.client.AuthServiceClient;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.lang.reflect.Method;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    ResourceInfo resourceInfo;

    @Inject
    @RestClient
    AuthServiceClient authServiceClient;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        if (isPublicEndpoint()) {
            return;
        }

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            abortUnauthorized(requestContext, "Token de autenticação não fornecido");
            return;
        }

        try {
            Response validation = authServiceClient.validate(authHeader);
            if (validation.getStatus() != Response.Status.OK.getStatusCode()) {
                abortUnauthorized(requestContext, "Token inválido ou sessão encerrada");
            }
        } catch (Exception e) {
            requestContext.abortWith(
                Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .type(MediaType.APPLICATION_JSON)
                    .entity("{\"error\": \"Serviço de autenticação indisponível\"}")
                    .build()
            );
        }
    }

    private boolean isPublicEndpoint() {
        Method method = resourceInfo.getResourceMethod();
        if (method != null && method.isAnnotationPresent(PublicEndpoint.class)) {
            return true;
        }
        Class<?> resourceClass = resourceInfo.getResourceClass();
        return resourceClass != null && resourceClass.isAnnotationPresent(PublicEndpoint.class);
    }

    private void abortUnauthorized(ContainerRequestContext context, String message) {
        context.abortWith(
            Response.status(Response.Status.UNAUTHORIZED)
                .type(MediaType.APPLICATION_JSON)
                .entity("{\"error\": \"" + message + "\"}")
                .build()
        );
    }
}
