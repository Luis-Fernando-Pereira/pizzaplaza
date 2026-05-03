package br.com.pizzaplaza.product.services;

import br.com.pizzaplaza.entity.dtos.CategoryDto;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryService {

    public CategoryDto save() {
        return new CategoryDto();
    }

}
