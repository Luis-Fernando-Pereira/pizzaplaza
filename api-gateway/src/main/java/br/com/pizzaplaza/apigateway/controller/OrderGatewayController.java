package br.com.pizzaplaza.apigateway.controller;

import br.com.pizzaplaza.apigateway.annotation.PublicEndpoint;
import br.com.pizzaplaza.apigateway.client.OrderServiceClient;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/orders")
@ApplicationScoped
public class OrderGatewayController {

    @Inject
    @RestClient
    OrderServiceClient orderServiceClient;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(Object body,
                                @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader) {
        return orderServiceClient.createOrder(body, authHeader);
    }

    @GET
    @Path("/{oid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrder(@PathParam("oid") String oid,
                             @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader) {
        return orderServiceClient.getOrder(oid, authHeader);
    }

    @GET
    @Path("/my")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findMyOrders(@HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader) {
        return orderServiceClient.findMyOrders(authHeader);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllOrders(@HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader) {
        return orderServiceClient.findAllOrders(authHeader);
    }

    @PATCH
    @Path("/{oid}/advance-status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response advanceStatus(@PathParam("oid") String oid,
                                  @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader) {
        return orderServiceClient.advanceStatus(oid, authHeader);
    }

    @GET
    @Path("/events")
    @PublicEndpoint
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<String> subscribeToEvents(@QueryParam("token") String token) {
        return orderServiceClient.subscribeToEvents(token);
    }
}
