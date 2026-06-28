package br.com.pizzaplaza.userservice.repository;

import br.com.pizzaplaza.userservice.entities.Admin;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AdminRepository {

    @Inject
    EntityManager em;

    public Admin save(Admin admin) {
        em.persist(admin);
        return admin;
    }

    public Admin update(Admin admin) {
        return em.merge(admin);
    }

    public void delete(Admin admin) {
        em.remove(em.contains(admin) ? admin : em.merge(admin));
    }

    public List<Admin> findAll() {
        return em.createQuery("SELECT a FROM Admin a LEFT JOIN FETCH a.user", Admin.class)
                .getResultList();
    }

    public Optional<Admin> findByOid(String oid) {
        return em.createQuery(
                "SELECT a FROM Admin a LEFT JOIN FETCH a.user WHERE a.oid = :oid", Admin.class)
                .setParameter("oid", oid)
                .getResultStream()
                .findFirst();
    }

    public Optional<Admin> findByUserOid(String userOid) {
        return em.createQuery(
                "SELECT a FROM Admin a LEFT JOIN FETCH a.user u WHERE u.oid = :oid", Admin.class)
                .setParameter("oid", userOid)
                .getResultStream()
                .findFirst();
    }
}
