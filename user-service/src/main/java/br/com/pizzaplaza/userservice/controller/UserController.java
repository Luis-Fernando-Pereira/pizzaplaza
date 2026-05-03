package br.com.pizzaplaza.userservice.controller;

import br.com.pizzaplaza.entity.dto.UserDto;
import br.com.pizzaplaza.entity.enums.UserType;
import br.com.pizzaplaza.userservice.service.UserService;
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

    @Path("/{type}")
    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@PathParam("type") String type, @Valid UserDto userDto) {
        try {

            UserType userType = UserType.valueOf(type.toUpperCase());

            UserDto saved = userService.save(userDto, userType);

            return Response.created(createUri(saved)).entity(saved).build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }
    }

    @GET
    @Path("/{type}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@PathParam("type") String type) {

        try {
            UserType userType = UserType.valueOf(type.toUpperCase());

            List<UserDto> users = userService.findAll(userType);

            return Response.ok(users).build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }
    }

    @GET
    @Path("/{type}/{oid}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("type") String type, @PathParam("oid") String oid) {

        try {
            UserType userType = UserType.valueOf(type.toUpperCase());

            UserDto userDto = userService.findByOid(userType, oid);

            return Response.ok(userDto).build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }
    }

    @DELETE
    @Path("/{type}/{oid}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("type") String type, @PathParam("oid") String oid) {

        try {
            UserType userType = UserType.valueOf(type.toUpperCase());

            userService.delete(oid, userType);

            return Response.noContent().build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }
    }

    public URI createUri(UserDto userDto) {
        return URI.create("/users/" + userDto.getOid());
    }
}
