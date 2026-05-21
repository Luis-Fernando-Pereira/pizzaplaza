package br.com.pizzaplaza.orderservice.services;

import br.com.pizzaplaza.orderservice.integration.productservice.FlavorGateway;
import br.com.pizzaplaza.orderservice.dtos.PizzaDto;
import io.smallrye.common.constraint.NotNull;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class PizzaService {

    @Inject
    FlavorGateway flavorClientContract;

    public void savePizzaList(@NotNull List<PizzaDto> pizzaList) {

    }

}
