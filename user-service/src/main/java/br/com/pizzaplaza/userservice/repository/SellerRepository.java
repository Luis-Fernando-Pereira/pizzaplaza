package br.com.pizzaplaza.userservice.repository;

import br.com.pizzaplaza.userservice.entities.Seller;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SellerRepository {

    @Inject
    EntityManager em;

    public Seller save(Seller seller) {
        em.persist(seller);
        return seller;
    }

    public Seller update(Seller seller) {
        return em.merge(seller);
    }

    public void delete(Seller seller) {
        em.remove(em.contains(seller) ? seller : em.merge(seller));
    }

    public List<Seller> findAll() {
        return em.createQuery("SELECT s FROM Seller s LEFT JOIN FETCH s.user", Seller.class)
                .getResultList();
    }

    public Optional<Seller> findByOid(String oid) {
        return em.createQuery(
                "SELECT s FROM Seller s LEFT JOIN FETCH s.user WHERE s.oid = :oid", Seller.class)
                .setParameter("oid", oid)
                .getResultStream()
                .findFirst();
    }

    public Optional<Seller> findByUserOid(String userOid) {
        return em.createQuery(
                "SELECT s FROM Seller s LEFT JOIN FETCH s.user u WHERE u.oid = :oid", Seller.class)
                .setParameter("oid", userOid)
                .getResultStream()
                .findFirst();
    }
}
