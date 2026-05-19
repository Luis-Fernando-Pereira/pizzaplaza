package br.com.pizzaplaza.orderservice.repositories;

import br.com.pizzaplaza.entity.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class OrderRepository {

    @Inject
    EntityManager em;

    public void persist(Order order) {
        em.persist(order);
    }
}
