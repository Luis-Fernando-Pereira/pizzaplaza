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

    public void save(Flavor category) {
        em.persist(category);
    }

    public Flavor update(Flavor category) {
        return em.merge(category);
    }

    public void delete(Flavor category) {
        em.remove(category);
    }

    public Flavor findByOid(String oid) {
        return em.find(Flavor.class, oid);
    }

    public List<Flavor> findAll() {
        return em.createQuery("SELECT c FROM Flavor c", Flavor.class)
                .getResultList();
    }

}
