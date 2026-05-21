package br.com.pizzaplaza.orderservice.controllers;

import br.com.pizzaplaza.orderservice.entities.Order;
import br.com.pizzaplaza.orderservice.dtos.OrderDto;
import br.com.pizzaplaza.orderservice.services.OrderService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
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

            return Response.created(new URI("")).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
