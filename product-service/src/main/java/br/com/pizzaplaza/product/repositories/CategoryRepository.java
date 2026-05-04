package br.com.pizzaplaza.product.repositories;

import br.com.pizzaplaza.entity.Category;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class CategoryRepository {

    @Inject
    EntityManager em;

    public void save(Category category) {
        em.persist(category);
    }

    public Category update(Category category) {
        return em.merge(category);
    }

    public void delete(Category category) {
        em.remove(category);
    }

    public Category findByOid(String oid) {
        return em.find(Category.class, oid);
    }

    public List<Category> findAll() {
        return em.createQuery("SELECT c FROM Category c", Category.class)
                .getResultList();
    }

}
