package br.com.pizzaplaza.product.repositories;

import br.com.pizzaplaza.entity.Category;
import br.com.pizzaplaza.entity.Flavor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class FlavorRepository {

    @Inject
    EntityManager em;

    public void save(Flavor flavor) {
        em.persist(flavor);
    }

    public Flavor update(Flavor flavor) {
        return em.merge(flavor);
    }

    public void delete(Flavor flavor) {
        em.remove(flavor);
    }

    public Flavor findByOid(String oid) {
        return em.find(Flavor.class, oid);
    }

    public List<Flavor> findAll() {
        return em.createQuery("SELECT f FROM Flavor f", Flavor.class)
                .getResultList();
    }

}
