package br.com.pizzaplaza.apigateway.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "product-service")
public interface ProductServiceClient {

    // ── Categories ────────────────────────────────────────────────────────────

    @GET
    @Path("/categories")
    @Produces(MediaType.APPLICATION_JSON)
    Response getCategories();

    @GET
    @Path("/categories/{oid}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getCategory(@PathParam("oid") String oid);

    @POST
    @Path("/categories")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response createCategory(Object body, @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader);

    @PUT
    @Path("/categories")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response updateCategory(Object body, @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader);

    @DELETE
    @Path("/categories/{oid}")
    Response deleteCategory(@PathParam("oid") String oid, @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader);

    // ── Flavors ───────────────────────────────────────────────────────────────

    @GET
    @Path("/flavors")
    @Produces(MediaType.APPLICATION_JSON)
    Response getFlavors(@QueryParam("oidList") List<String> oids);

    @GET
    @Path("/flavors/{oid}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getFlavor(@PathParam("oid") String oid);

    @POST
    @Path("/flavors")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response createFlavor(Object body, @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader);

    @PUT
    @Path("/flavors")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response updateFlavor(Object body, @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader);

    @DELETE
    @Path("/flavors/{oid}")
    Response deleteFlavor(@PathParam("oid") String oid, @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader);
}
