package br.com.pizzaplaza.userservice.controller;

import br.com.pizzaplaza.entity.dto.UserDto;
import br.com.pizzaplaza.entity.enums.UserType;
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
public class UserController {

    @Inject
    UserService userService;

    @Path("/client")
    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newClient(@Valid UserDto userDto) {
        try {

            userDto = userService.save(userDto, UserType.CLIENT);

            return Response.created(userService.createUri(userDto)).entity(userDto).build();

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

            userDto = userService.save(userDto, UserType.ADMIN);

            return Response.created(userService.createUri(userDto)).entity(userDto).build();

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

            userDto = userService.save(userDto, UserType.SELLER);

            return Response.created(userService.createUri(userDto)).entity(userDto).build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }
    }

    @GET
    @Path("/admin")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllAdmin() {

        try {

            List<UserDto> users = userService.findAll(UserType.ADMIN);

            return Response.ok(users).build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }
    }

    @GET
    @Path("/admin/{oid}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAdmin(@PathParam("oid") String oid) {

        try {

            UserDto userDto = userService.findByOid(UserType.ADMIN, oid);

            return Response.ok(userDto).build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }
    }

    @GET
    @Path("/seller")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllSeller() {

        try {

            List<UserDto> users = userService.findAll(UserType.SELLER);

            return Response.ok(users).build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }
    }

    @GET
    @Path("/seller/{oid}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findSeller(@PathParam("oid") String oid) {

        try {

            UserDto userDto = userService.findByOid(UserType.SELLER, oid);

            return Response.ok(userDto).build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }
    }

    @GET
    @Path("/client")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllClient() {

        try {

            List<UserDto> users = userService.findAll(UserType.CLIENT);

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

            UserDto userDto = userService.findByOid(UserType.CLIENT, oid);

            return Response.ok(userDto).build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }
    }
}
