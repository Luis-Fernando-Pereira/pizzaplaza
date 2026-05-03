package br.com.pizzaplaza.userservice.controller;

import br.com.pizzaplaza.entity.dto.UserDto;
import br.com.pizzaplaza.userservice.service.UserService;
import br.com.pizzaplaza.userservice.strategies.AdminStrategy;
import br.com.pizzaplaza.userservice.strategies.ClientStrategy;
import br.com.pizzaplaza.userservice.strategies.SellerStrategy;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@ApplicationScoped
@Path("/users")
public class ClientController {

    @Inject
    UserService userService;

    @Inject
    ClientStrategy clientStrategy;

    @GET
    @Path("/client")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllClient() {

        try {

            List<UserDto> users = userService.findAll(clientStrategy);

            return Response.ok(users).build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }
    }

    @GET
    @Path("/client/{oid}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findClient(@PathParam("oid") String oid) {

        try {

            UserDto userDto = userService.findByOid(clientStrategy, oid);

            return Response.ok(userDto).build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }
    }
}
