package br.com.pizzaplaza.orderservice.repositories;

import br.com.pizzaplaza.entity.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrderRepository {

    @Inject
    EntityManager em;

    public void persist(Order order) {
        em.persist(order);
    }

    public Optional<Order> findByOidOptional(String oid) {
        return em.createQuery("SELECT * from Order o where o.oid = :oid", Order.class)
                .setParameter("oid", oid)
                .getResultStream()
                .findFirst();
    }

    public List<Order> listAll() {
        return em.createQuery("SELECT o from Order o", Order.class).getResultList();
    }
}
