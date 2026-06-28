package br.com.pizzaplaza.apigateway.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "user-service")
@Path("/users")
public interface UserServiceClient {

    @POST
    @Path("/{type}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response createUser(@PathParam("type") String type, Object body);

    @PUT
    @Path("/{type}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response updateUser(@PathParam("type") String type, Object body,
                        @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader);

    @GET
    @Path("/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    Response findAll(@PathParam("type") String type,
                     @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader);

    @GET
    @Path("/{type}/{oid}")
    @Produces(MediaType.APPLICATION_JSON)
    Response find(@PathParam("type") String type, @PathParam("oid") String oid,
                  @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader);

    @DELETE
    @Path("/{type}/{oid}")
    Response delete(@PathParam("type") String type, @PathParam("oid") String oid,
                    @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader);
}
