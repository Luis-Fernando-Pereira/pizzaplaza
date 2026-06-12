package br.com.pizzaplaza.userservice.repository;

//import br.com.pizzaplaza.entity.actors.Client;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClientRepository {
//
//    @Inject
//    EntityManager em;
//
//    public void delete(Client client) {
//        em.remove(client);
//    }
//
//    public Client save(Client client) {
//        em.persist(client);
//        return client;
//    }
//
//    public Client update(Client client) {
//        return em.merge(client);
//    }
//
//    public List<Client> findAll() {
//        return em.createQuery("SELECT c from Client c LEFT JOIN FETCH c.user", Client.class).getResultList();
//    }
//
//    public Optional<Client> findByOid(String oid) {
//        return em.createQuery(
//                        "SELECT c FROM Client c LEFT JOIN FETCH c.user WHERE c.oid = :oid",
//                        Client.class
//                )
//                .setParameter("oid", oid)
//                .getResultStream()
//                .findFirst();
//    }
//
//    public Optional<Client> findByUserOid(String oid) {
//        return em.createQuery(
//                        "SELECT c FROM Client c LEFT JOIN FETCH c.user u WHERE u.oid = :oid",
//                        Client.class
//                )
//                .setParameter("oid", oid)
//                .getResultStream()
//                .findFirst();
//    }
}
