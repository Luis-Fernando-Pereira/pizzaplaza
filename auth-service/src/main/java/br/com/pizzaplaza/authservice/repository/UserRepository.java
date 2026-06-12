package br.com.pizzaplaza.authservice.repository;

//import br.com.pizzaplaza.entity.actors.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

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
//    public User findByEmail(String email) {
//        return em.createQuery("SELECT u from User u where u.email = :email", User.class)
//                .setParameter("email", email)
//                .getSingleResult();
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
//    public Boolean emailAlreadyInUse(String email) {
//        return em.createQuery("SELECT u from User u where u.email = :email", User.class)
//                .setParameter("email", email)
//                .getSingleResult() != null;
//    }
//
//    public User update(User user) {
//        return em.merge(user);
//    }
}
