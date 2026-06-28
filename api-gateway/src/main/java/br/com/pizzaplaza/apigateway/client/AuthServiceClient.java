package br.com.pizzaplaza.apigateway.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "auth-service")
@Path("/auth")
public interface AuthServiceClient {

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response register(Object body);

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    Response login(Object body);

    @GET
    @Path("/validate")
    @Produces(MediaType.APPLICATION_JSON)
    Response validate(@HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader);

    @POST
    @Path("/refresh")
    @Produces(MediaType.TEXT_PLAIN)
    Response refresh(@HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader);

    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    Response logout(@HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader);
}
