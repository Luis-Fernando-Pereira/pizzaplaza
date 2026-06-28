package br.com.pizzaplaza.authservice.controller;

import br.com.pizzaplaza.authservice.dtos.LoginDto;
import br.com.pizzaplaza.authservice.dtos.UserRegistrationDto;
import br.com.pizzaplaza.authservice.entities.User;
import br.com.pizzaplaza.authservice.service.AuthService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@ApplicationScoped
public class AuthController {

    @Inject
    AuthService authService;

    @POST
    @Path("/register")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@Valid UserRegistrationDto dto) {
        try {
            User user = authService.register(dto);
            return Response.status(Response.Status.CREATED)
                    .entity("{\"oid\": \"" + user.getOid() + "\"}")
                    .build();
        } catch (IllegalStateException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/login")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(@Valid LoginDto loginData) {
        try {
            User user = authService.authenticate(loginData);
            return Response.ok(authService.generateJwt(user)).build();
        } catch (NotAuthorizedException e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(e.getMessage())
                    .build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/refresh")
    @RolesAllowed({"admin", "seller", "client"})
    @Produces(MediaType.TEXT_PLAIN)
    public Response refresh() {
        try {
            User user = authService.refresh();
            return Response.ok(authService.generateJwt(user)).build();
        } catch (NotAuthorizedException e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/logout")
    @RolesAllowed({"admin", "seller", "client"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout() {
        try {
            authService.logout();
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/validate")
    @RolesAllowed({"admin", "seller", "client"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response validate() {
        try {
            authService.validateSession();
            return Response.ok().build();
        } catch (NotAuthorizedException e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
