package br.com.pizzaplaza.orderservice.integration.productservice;

import br.com.pizzaplaza.contracts.dtos.FlavorResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Set;


@ApplicationScoped
public class FlavorIntegrator implements FlavorGateway {

    @Inject
    @RestClient
    FlavorClient flavorClient;

    @Override
    public FlavorResponseDto findByOid(String oid) {
        try {
            return flavorClient.findByOid(oid);

        } catch (WebApplicationException webAppException) {

            throw new RuntimeException("Erro ao buscar flavor no Product Service. Status: " + webAppException.getResponse().getStatus(), webAppException);

        } catch (Exception exception) {

            throw new RuntimeException("Erro inesperado ao integrar com Product Service", exception);
        }
    }

    @Override
    public Set<FlavorResponseDto> findByOids(Set<String> oids) {
        try {

            return flavorClient.findByOids(oids);

        } catch (WebApplicationException webAppException) {

            throw new RuntimeException("Erro ao buscar flavors no Product Service. Status: " + webAppException.getResponse().getStatus(), webAppException);

        } catch (Exception exception) {

            throw new RuntimeException("Erro inesperado ao integrar com Product Service", exception);
        }
    }
}
