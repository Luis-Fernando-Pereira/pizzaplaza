package br.com.pizzaplaza.userservice.repository;

//import br.com.pizzaplaza.entity.actors.Seller;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SellerRepository {
//
//    @Inject
//    EntityManager em;
//
//    public void delete(Seller seller) {
//        em.remove(seller);
//    }
//
//    public Optional<Seller> findByUserOid(String oid) {
//        return em.createQuery(
//                        "SELECT s FROM Seller s LEFT JOIN FETCH s.user u WHERE u.oid = :oid",
//                        Seller.class
//                )
//                .setParameter("oid", oid)
//                .getResultStream()
//                .findFirst();
//    }
//
//    public Seller update(Seller client) {
//        return em.merge(client);
//    }
//
//    public Seller save(Seller seller) {
//        em.persist(seller);
//        return seller;
//    }
//
//    public Optional<Seller> findByOid(String oid) {
//        return em.createQuery(
//                        "SELECT s FROM Seller s LEFT JOIN FETCH s.user WHERE s.oid = :oid",
//                        Seller.class
//                )
//                .setParameter("oid", oid)
//                .getResultStream()
//                .findFirst();
//    }
//
//    public List<Seller> findAll() {
//        return em.createQuery("SELECT s from Seller s LEFT JOIN FETCH s.user", Seller.class).getResultList();
//    }

}
