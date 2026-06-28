package br.com.pizzaplaza.orderservice.controllers;

import br.com.pizzaplaza.orderservice.dtos.OrderDto;
import br.com.pizzaplaza.orderservice.services.OrderService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/orders")
public class OrderController {

    @Inject
    OrderService orderService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByOid(@PathParam("oid") String oid) {
        try {
            OrderDto dto = orderService.find(oid);
            return Response.ok(dto).build();
        } catch (NotFoundException notFoundException) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/my")
    @RolesAllowed("client")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findMyOrders() {
        try {
            List<OrderDto> dtoList = orderService.findByCurrentUser();
            return Response.ok(dtoList).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            List<OrderDto> dtoList = orderService.findAll();
            return Response.ok(dtoList).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
