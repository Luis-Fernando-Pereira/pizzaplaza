package br.com.pizzaplaza.product.controllers;

import br.com.pizzaplaza.entity.dtos.CategoryDto;
import br.com.pizzaplaza.entity.dtos.UserDto;
import br.com.pizzaplaza.product.services.CategoryService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/categories")
public class CategoryController {

    @Inject
    CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok().build();
    }

    @GET
    @Path("/{oid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("oid") String oid) {
        return Response.ok().build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(@Valid CategoryDto dto) {

        try {

            dto = categoryService.save(dto);

            return Response.created(createUri(dto)).entity(dto).build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }

    }

    @PUT
    @Path("{oid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(String json) {
        return Response.ok().build();
    }

    @DELETE
    @Path("{oid}")
    public Response delete(String json) {
        return Response.noContent().build();
    }

    public URI createUri(CategoryDto dto) {
        return URI.create("/categories/" + dto.getOid());
    }
}
