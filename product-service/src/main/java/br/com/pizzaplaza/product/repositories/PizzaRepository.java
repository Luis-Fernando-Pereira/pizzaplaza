package br.com.pizzaplaza.product.repositories;

import br.com.pizzaplaza.entity.Pizza;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@ApplicationScoped
public class PizzaRepository {

    @Inject
    EntityManager em;

    public void save(@NotNull Pizza pizza) {
        em.persist(pizza);
    }

    public Pizza update(Pizza pizza) {
        return em.merge(pizza);
    }

    public void delete(Pizza pizza) {
        em.remove(pizza);
    }

    public Pizza findByOid(String oid) {
        return em.find(Pizza.class, oid);
    }

    public List<Pizza> findAll() {
        return em.createQuery("SELECT p FROM Pizza p", Pizza.class)
                .getResultList();
    }

}
