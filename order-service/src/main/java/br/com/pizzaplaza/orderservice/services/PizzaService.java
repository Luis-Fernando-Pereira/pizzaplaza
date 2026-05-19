package br.com.pizzaplaza.orderservice.services;

import br.com.pizzaplaza.orderservice.integration.productservice.FlavorIntegrator;
import br.com.pizzaplaza.orderservice.interfaces.Integrable;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PizzaService {
    Integrable flavorIntegration = new FlavorIntegrator();

}
