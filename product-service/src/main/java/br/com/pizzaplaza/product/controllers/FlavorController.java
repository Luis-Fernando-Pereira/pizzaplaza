package br.com.pizzaplaza.product.controllers;

import br.com.pizzaplaza.product.libraries.FlavorDto;
import br.com.pizzaplaza.product.services.FlavorService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/flavors")
public class FlavorController {

    @Inject
    FlavorService flavorService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@QueryParam("oidList") List<String> oids) {
        List<FlavorDto> dtos = flavorService.findAll(oids);

        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{oid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("oid") String oid) {

        FlavorDto dto = flavorService.find(oid);

        return Response.ok(dto).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(@Valid FlavorDto dto) {

        FlavorDto saved = flavorService.save(dto);

         return Response.created(createUri(saved)).entity(saved).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid FlavorDto dto) {

        flavorService.update(dto);

        return Response.ok().build();
    }

    @DELETE
    @Path("{oid}")
    public Response delete(String oid) {

        flavorService.delete(oid);

        return Response.noContent().build();
    }

    public URI createUri(FlavorDto dto) {
        return URI.create("/flavors/" + dto.getOid());
    }
}
