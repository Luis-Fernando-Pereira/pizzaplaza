package br.com.pizzaplaza.product.controllers;

import br.com.pizzaplaza.entity.dtos.CategoryDto;
import br.com.pizzaplaza.entity.dtos.UserDto;
import br.com.pizzaplaza.product.services.CategoryService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/categories")
public class CategoryController {

    @Inject
    CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {

        List<CategoryDto> dtos = categoryService.findAll();

        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{oid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("oid") @NotBlank String oid) {

        CategoryDto dto = categoryService.find(oid);

        return Response.ok(dto).build();
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
    public Response update(@Valid CategoryDto dto) {

        categoryService.update(dto);

        return Response.ok(dto).build();
    }

    @DELETE
    @Path("{oid}")
    public Response delete(@PathParam("oid") @NotBlank String oid) {

        categoryService.delete(oid);

        return Response.noContent().build();
    }

    public URI createUri(CategoryDto dto) {
        return URI.create("/categories/" + dto.getOid());
    }
}
