package br.com.pizzaplaza.orderservice.integration.productservice;

import br.com.pizzaplaza.contracts.dtos.FlavorResponseDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Set;

@Path("/flavors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "product-service")
public interface FlavorClient {

    @GET
    @Path("/{oid}")
    FlavorResponseDto findByOid(@PathParam("oid") String oid);

    @POST
    @Path("/by-oids")
    Set<FlavorResponseDto> findByOids(Set<String> oids);

}
