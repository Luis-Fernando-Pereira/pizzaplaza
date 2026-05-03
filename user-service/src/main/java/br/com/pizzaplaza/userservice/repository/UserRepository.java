package br.com.pizzaplaza.userservice.repository;

import br.com.pizzaplaza.entity.systemactor.Admin;
import br.com.pizzaplaza.entity.systemactor.Client;
import br.com.pizzaplaza.entity.systemactor.Seller;
import br.com.pizzaplaza.entity.systemactor.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserRepository {

    @Inject
    EntityManager em;

    public Optional<Admin> findAdminByOid(String oid) {
        return em.createQuery(
                        "SELECT a FROM Admin a LEFT JOIN FETCH a.user WHERE a.oid = :oid",
                        Admin.class
                )
                .setParameter("oid", oid)
                .getResultStream()
                .findFirst();
    }

    public Optional<Client> findClientByOid(String oid) {
        return em.createQuery(
                        "SELECT c FROM Client c LEFT JOIN FETCH c.user WHERE c.oid = :oid",
                        Client.class
                )
                .setParameter("oid", oid)
                .getResultStream()
                .findFirst();
    }

    public Optional<Seller> findSellerByOid(String oid) {
        return em.createQuery(
                        "SELECT s FROM Seller s LEFT JOIN FETCH s.user WHERE s.oid = :oid",
                        Seller.class
                )
                .setParameter("oid", oid)
                .getResultStream()
                .findFirst();
    }

    public List<Admin> findAllUserAdmin() {
        return em.createQuery("SELECT a FROM Admin a LEFT JOIN FETCH a.user", Admin.class).getResultList();
    }

    public List<Client> findAllUserClient() {
        return em.createQuery("SELECT c from Client c LEFT JOIN FETCH c.user", Client.class).getResultList();
    }

    public List<Seller> findAllUserSeller() {
        return em.createQuery("SELECT s from Seller s LEFT JOIN FETCH s.user", Seller.class).getResultList();
    }

    public User save(User user) {
        em.persist(user);
        return user;
    }

    public User findByEmail(String email) {
        return em.createQuery("SELECT u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public Boolean isEmailInUse(String email) {
        return em.createQuery("SELECT count(u) from User u where u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult() > 0;
    }

    public Boolean isCpfInUse(String cpf) {
        return em.createQuery("SELECT count(u) from User u where u.cpf = :cpf", Long.class)
                .setParameter("cpf", cpf)
                .getSingleResult() > 0;
    }

    public Boolean emailAlreadyInUse(String email) {
        return em.createQuery("SELECT u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult() != null;
    }

    public User update(User user) {
        return em.merge(user);
    }
}
