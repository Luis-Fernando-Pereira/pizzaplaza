package br.com.pizzaplaza.product.services;

import br.com.pizzaplaza.entity.Category;
import br.com.pizzaplaza.entity.dtos.CategoryDto;
import br.com.pizzaplaza.product.repositories.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

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

        if (category == null) {
            throw new NotFoundException();
        }

        return new CategoryDto(category);
    }

    public List<CategoryDto> findAll() {

        List<Category> results = categoryRepository.findAll();

        if (results == null) {
            return new ArrayList<>();
        }

        List<CategoryDto> dtos = results.stream().map(CategoryDto::new).toList();

        return dtos;
    }

}
