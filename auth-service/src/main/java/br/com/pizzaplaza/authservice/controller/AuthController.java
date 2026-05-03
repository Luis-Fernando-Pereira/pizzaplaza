package br.com.pizzaplaza.authservice.controller;

import br.com.pizzaplaza.authservice.service.AuthService;
import br.com.pizzaplaza.entity.dto.LoginDto;
import br.com.pizzaplaza.entity.systemactor.User;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@ApplicationScoped
public class AuthController {

    @Inject
    AuthService authService;

    @POST
    @Path("/login")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(@Valid LoginDto loginData) {
        try {

            User user = authService.authenticate(loginData);

            if (user == null) {
                return Response.status(400).build();
            }

            return Response.ok(authService.generateJwt(user)).build();

        } catch (Exception e) {
            return Response.status(400).build();
        }
    }

    @POST
    @Path("/refresh")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public Response auth() {
        try {

            User user = authService.authenticate();

            return Response.ok(authService.generateJwt(user)).build();

        } catch (Exception e) {
            return Response.status(400).build();
        }
    }

}
