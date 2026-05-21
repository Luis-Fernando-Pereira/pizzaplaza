package br.com.pizzaplaza.product.integration.productservice.client;

import br.com.pizzaplaza.entity.dtos.FlavorDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/flavors")
@RegisterRestClient(configKey = "product-service")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface FlavorClient {
    @GET
    @Path("/{oid}")
    FlavorDto findByOid(@PathParam("oid") String oid);
}
