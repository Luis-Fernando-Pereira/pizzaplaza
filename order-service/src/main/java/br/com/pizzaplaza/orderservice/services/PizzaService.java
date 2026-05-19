package br.com.pizzaplaza.orderservice.services;

import br.com.pizzaplaza.orderservice.integration.productservice.FlavorIntegrator;
import br.com.pizzaplaza.orderservice.interfaces.FlavorGateway;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PizzaService {
    FlavorGateway flavorIntegration = new FlavorIntegrator();

}
