package br.com.pizzaplaza.orderservice;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/orders")
public class OrderController {

    @POST
    public Response post() {
        try {
            return Response.created(new URI("")).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
