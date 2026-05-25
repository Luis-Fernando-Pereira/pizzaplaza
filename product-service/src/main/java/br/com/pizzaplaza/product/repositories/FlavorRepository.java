package br.com.pizzaplaza.product.repositories;

import br.com.pizzaplaza.product.entities.Flavor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

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

    public List<Flavor> findAll(List<String> oidList) {
        TypedQuery<Flavor> query;

        if (oidList != null && oidList.size() > 0) {
            query = em.createQuery("SELECT f FROM Flavor f where f.oid in :oids", Flavor.class);
            query.setParameter("oids", oidList);
        } else {
            query = em.createQuery("SELECT f FROM Flavor f", Flavor.class);
        }

        return query.getResultList();
    }

}
