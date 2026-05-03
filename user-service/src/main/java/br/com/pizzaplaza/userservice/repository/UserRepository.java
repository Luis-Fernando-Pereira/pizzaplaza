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
