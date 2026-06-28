package br.com.pizzaplaza.userservice.repository;

import br.com.pizzaplaza.userservice.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.Optional;

@ApplicationScoped
public class UserRepository {

    @Inject
    EntityManager em;

    public User save(User user) {
        em.persist(user);
        return user;
    }

    public void detach(User user) {
        em.detach(user);
    }

    public Optional<User> findByEmail(String email) {
        return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }

    public Optional<User> findByOid(String oid) {
        return em.createQuery("SELECT u FROM User u WHERE u.oid = :oid", User.class)
                .setParameter("oid", oid)
                .getResultStream()
                .findFirst();
    }

    public boolean isEmailInUse(String email) {
        Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();
        return count > 0;
    }

    public boolean isCpfInUse(String cpf) {
        Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.cpf = :cpf", Long.class)
                .setParameter("cpf", cpf)
                .getSingleResult();
        return count > 0;
    }

    public User update(User user) {
        return em.merge(user);
    }
}
