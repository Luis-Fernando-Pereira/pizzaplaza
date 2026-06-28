package br.com.pizzaplaza.apigateway.client;

import io.smallrye.mutiny.Multi;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "order-service")
@Path("/orders")
public interface OrderServiceClient {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response createOrder(Object body, @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader);

    @GET
    @Path("/{oid}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getOrder(@PathParam("oid") String oid, @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader);

    @GET
    @Path("/my")
    @Produces(MediaType.APPLICATION_JSON)
    Response findMyOrders(@HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response findAllOrders(@HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader);

    @PATCH
    @Path("/{oid}/advance-status")
    @Produces(MediaType.APPLICATION_JSON)
    Response advanceStatus(@PathParam("oid") String oid, @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader);

    @GET
    @Path("/events")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    Multi<String> subscribeToEvents(@QueryParam("token") String token);
}
