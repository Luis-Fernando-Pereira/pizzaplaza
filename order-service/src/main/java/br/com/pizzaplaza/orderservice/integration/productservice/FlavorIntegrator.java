package br.com.pizzaplaza.orderservice.integration.productservice;

import br.com.pizzaplaza.entity.dtos.FlavorDto;
import br.com.pizzaplaza.orderservice.integration.productservice.client.FlavorClient;
import br.com.pizzaplaza.orderservice.interfaces.FlavorGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Named("rest")
@ApplicationScoped
public class FlavorIntegrator implements FlavorGateway {

    @Inject
    @RestClient
    FlavorClient flavorClient;

    @Override
    public FlavorDto find(String oid) {
        try {
            return flavorClient.findByOid(oid);

        } catch (NotFoundException e) {
            throw new IllegalArgumentException(
                    "Flavor não encontrado: " + oid
            );

        } catch (Exception e) {
            throw new RuntimeException(
                    "Erro ao integrar com Product Service",
                    e
            );
        }
    }
}
