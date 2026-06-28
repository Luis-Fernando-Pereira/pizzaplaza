package br.com.pizzaplaza.apigateway.controller;

import br.com.pizzaplaza.apigateway.annotation.PublicEndpoint;
import br.com.pizzaplaza.apigateway.client.ProductServiceClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/api/products")
@ApplicationScoped
public class ProductGatewayController {

    @Inject
    @RestClient
    ProductServiceClient productServiceClient;

    // ── Categories ────────────────────────────────────────────────────────────

    @GET
    @Path("/categories")
    @PublicEndpoint
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories() {
        return productServiceClient.getCategories();
    }

    @GET
    @Path("/categories/{oid}")
    @PublicEndpoint
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory(@PathParam("oid") String oid) {
        return productServiceClient.getCategory(oid);
    }

    @POST
    @Path("/categories")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategory(Object body,
                                   @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader) {
        return productServiceClient.createCategory(body, authHeader);
    }

    @PUT
    @Path("/categories")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategory(Object body,
                                   @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader) {
        return productServiceClient.updateCategory(body, authHeader);
    }

    @DELETE
    @Path("/categories/{oid}")
    public Response deleteCategory(@PathParam("oid") String oid,
                                   @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader) {
        return productServiceClient.deleteCategory(oid, authHeader);
    }

    // ── Flavors ───────────────────────────────────────────────────────────────

    @GET
    @Path("/flavors")
    @PublicEndpoint
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlavors(@QueryParam("oidList") List<String> oids) {
        return productServiceClient.getFlavors(oids);
    }

    @GET
    @Path("/flavors/{oid}")
    @PublicEndpoint
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlavor(@PathParam("oid") String oid) {
        return productServiceClient.getFlavor(oid);
    }

    @POST
    @Path("/flavors")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFlavor(Object body,
                                 @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader) {
        return productServiceClient.createFlavor(body, authHeader);
    }

    @PUT
    @Path("/flavors")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFlavor(Object body,
                                 @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader) {
        return productServiceClient.updateFlavor(body, authHeader);
    }

    @DELETE
    @Path("/flavors/{oid}")
    public Response deleteFlavor(@PathParam("oid") String oid,
                                 @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader) {
        return productServiceClient.deleteFlavor(oid, authHeader);
    }
}
