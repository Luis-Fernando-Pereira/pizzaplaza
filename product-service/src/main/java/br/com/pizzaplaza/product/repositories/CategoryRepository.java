package br.com.pizzaplaza.product.repositories;

import br.com.pizzaplaza.entity.Category;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class CategoryRepository {

    @Inject
    EntityManager em;

    public void save(Category category) {
        em.persist(category);
    }

}
