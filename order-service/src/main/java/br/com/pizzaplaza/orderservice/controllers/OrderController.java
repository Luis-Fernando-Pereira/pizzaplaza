package br.com.pizzaplaza.orderservice.controllers;

import br.com.pizzaplaza.orderservice.dtos.OrderDto;
import br.com.pizzaplaza.orderservice.dtos.OrderStatusEventDto;
import br.com.pizzaplaza.orderservice.services.OrderService;
import br.com.pizzaplaza.orderservice.services.OrderStatusNotifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.mutiny.Multi;
import jakarta.annotation.security.PermitAll;
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

    @Inject
    OrderStatusNotifier notifier;

    @Inject
    JWTParser jwtParser;

    @Inject
    ObjectMapper objectMapper;

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

    @PATCH
    @Path("/{oid}/advance-status")
    @RolesAllowed({"admin", "seller"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response advanceStatus(@PathParam("oid") String oid) {
        try {
            OrderDto updated = orderService.advanceStatus(oid);
            return Response.ok(updated).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/events")
    @PermitAll
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<String> subscribeToEvents(@QueryParam("token") String token) {
        if (token == null || token.isBlank()) {
            return Multi.createFrom().failure(new NotAuthorizedException("Token obrigatório"));
        }
        try {
            String userOid = jwtParser.parse(token).getSubject();
            return notifier.stream()
                    .filter(e -> userOid.equals(e.getUserOid()))
                    .map(this::toJson);
        } catch (Exception e) {
            return Multi.createFrom().failure(new NotAuthorizedException("Token inválido"));
        }
    }

    private String toJson(OrderStatusEventDto event) {
        try {
            return objectMapper.writeValueAsString(event);
        } catch (Exception e) {
            return "{}";
        }
    }
}
