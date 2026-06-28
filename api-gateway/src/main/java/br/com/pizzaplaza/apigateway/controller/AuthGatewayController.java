package br.com.pizzaplaza.apigateway.controller;

import br.com.pizzaplaza.apigateway.annotation.PublicEndpoint;
import br.com.pizzaplaza.apigateway.client.AuthServiceClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ClientWebApplicationException;

@Path("/api/auth")
@ApplicationScoped
public class AuthGatewayController {

    @Inject
    @RestClient
    AuthServiceClient authServiceClient;

    @POST
    @Path("/register")
    @PublicEndpoint
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(Object body) {
        try {
            return authServiceClient.register(body);
        } catch (ClientWebApplicationException e) {
            return e.getResponse();
        }
    }

    @POST
    @Path("/login")
    @PublicEndpoint
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(Object body) {
        try {
            return authServiceClient.login(body);
        } catch (ClientWebApplicationException e) {
            return e.getResponse();
        }
    }

    @POST
    @Path("/refresh")
    @Produces(MediaType.TEXT_PLAIN)
    public Response refresh(@HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader) {
        return authServiceClient.refresh(authHeader);
    }

    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader) {
        return authServiceClient.logout(authHeader);
    }
}
