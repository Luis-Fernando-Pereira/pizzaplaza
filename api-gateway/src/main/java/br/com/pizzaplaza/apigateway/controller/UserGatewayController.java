package br.com.pizzaplaza.apigateway.controller;

import br.com.pizzaplaza.apigateway.annotation.PublicEndpoint;
import br.com.pizzaplaza.apigateway.client.UserServiceClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/users")
@ApplicationScoped
public class UserGatewayController {

    @Inject
    @RestClient
    UserServiceClient userServiceClient;

    @POST
    @Path("/{type}")
    @PublicEndpoint
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@PathParam("type") String type, Object body) {
        return userServiceClient.createUser(type, body);
    }

    @PUT
    @Path("/{type}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("type") String type, Object body,
                               @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader) {
        return userServiceClient.updateUser(type, body, authHeader);
    }

    @GET
    @Path("/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@PathParam("type") String type,
                            @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader) {
        return userServiceClient.findAll(type, authHeader);
    }

    @GET
    @Path("/{type}/{oid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("type") String type, @PathParam("oid") String oid,
                         @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader) {
        return userServiceClient.find(type, oid, authHeader);
    }

    @DELETE
    @Path("/{type}/{oid}")
    public Response delete(@PathParam("type") String type, @PathParam("oid") String oid,
                           @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader) {
        return userServiceClient.delete(type, oid, authHeader);
    }
}
