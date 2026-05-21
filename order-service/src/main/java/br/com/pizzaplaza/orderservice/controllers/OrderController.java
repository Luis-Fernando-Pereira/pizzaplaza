package br.com.pizzaplaza.orderservice.controllers;

import br.com.pizzaplaza.orderservice.dtos.OrderDto;
import br.com.pizzaplaza.orderservice.services.OrderService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/orders")
public class OrderController {

    @Inject
    OrderService orderService;

    @POST
    public Response post(@Valid OrderDto dto) {
        try {

            OrderDto created = orderService.createOrder(dto);

            return Response.created(new URI("")).entity(created).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{oid}")
    public Response post(@PathParam("oid") String oid) {
        try {
            OrderDto dto = orderService.find(oid);
            return Response.ok(dto).build();
        } catch (NotFoundException notFoundException) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
