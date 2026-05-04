package br.com.pizzaplaza.product.services;

import br.com.pizzaplaza.entity.Category;
import br.com.pizzaplaza.entity.dtos.CategoryDto;
import br.com.pizzaplaza.product.repositories.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    @Transactional
    public CategoryDto save(CategoryDto dto) {
        Category category = new Category();

        category.setDescription(dto.description);

        categoryRepository.save(category);

        dto.setOid(category.getOid());
        dto.setCreatedAt(category.getCreatedAt());

        return dto;
    }

    public CategoryDto find(String oid) {

        Category category = categoryRepository.findByOid(oid);

        CategoryDto dto = new CategoryDto();

        dto.setOid(category.getOid());
        dto.setCreatedAt(category.getCreatedAt());
        dto.setDescription(category.getDescription());

        return dto;
    }

}
