package br.com.pizzaplaza.userservice.repository;

//import br.com.pizzaplaza.entity.actors.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.Optional;

@ApplicationScoped
public class UserRepository {
//
//    @Inject
//    EntityManager em;
//
//    public User save(User user) {
//        em.persist(user);
//        return user;
//    }
//
//    public void detach(User user) {
//        em.detach(user);
//    }
//
//    public User findByEmail(String email) {
//        return em.createQuery("SELECT u from User u where u.email = :email", User.class)
//                .setParameter("email", email)
//                .getSingleResult();
//    }
//
//    public Optional<User> findByOid(String oid) {
//        return em.createQuery(
//                        "SELECT u FROM User u WHERE u.oid = :oid",
//                        User.class
//                )
//                .setParameter("oid", oid)
//                .getResultStream()
//                .findFirst();
//    }
//
//    public Boolean isEmailInUse(String email) {
//        return em.createQuery("SELECT count(u) from User u where u.email = :email", Long.class)
//                .setParameter("email", email)
//                .getSingleResult() > 0;
//    }
//
//    public Boolean isCpfInUse(String cpf) {
//        return em.createQuery("SELECT count(u) from User u where u.cpf = :cpf", Long.class)
//                .setParameter("cpf", cpf)
//                .getSingleResult() > 0;
//    }
//
//    public User update(User user) {
//        return em.merge(user);
//    }
}
