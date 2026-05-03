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

@ApplicationScoped
@Path("/users")
public class UserController {

    @Inject
    UserService userService;

    @Inject
    ClientStrategy clientStrategy;

    @Inject
    AdminStrategy adminStrategy;

    @Inject
    SellerStrategy sellerStrategy;

    @Path("/client")
    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newClient(@Valid UserDto userDto) {
        try {

            userDto = userService.save(userDto, clientStrategy);

            URI uri = URI.create("/users/" + userDto.getOid());

            return Response.created(uri).entity(userDto).build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }
    }

    @Path("admin")
    @POST
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newAdmin(@Valid UserDto userDto) {
        try {

            userDto = userService.save(userDto, adminStrategy);

            URI uri = URI.create("/users/" + userDto.getOid());

            return Response.created(uri).entity(userDto).build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }
    }

    @Path("seller")
    @POST
    @RolesAllowed({"admin", "seller"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newSeller(UserDto userDto) {

        try {

            userDto = userService.save(userDto, sellerStrategy);

            URI uri = URI.create("/users/" + userDto.getOid());

            return Response.created(uri).entity(userDto).build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }
    }

    @GET
    @Path("/admin")
    @RolesAllowed({"admin", "seller"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllAdmin() {

        try {

            userDto = userService.findAll(adminStrategy);

            URI uri = URI.create("/users/" + userDto.getOid());

            return Response.created(uri).entity(userDto).build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }
    }

    @GET
    @Path("/admin/{oid}")
    @RolesAllowed({"admin", "seller"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAdmin(@PathParam("oid") String oid) {

        try {

            userDto = userService.save(userDto, sellerStrategy);

            URI uri = URI.create("/users/" + userDto.getOid());

            return Response.created(uri).entity(userDto).build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }
    }
}
