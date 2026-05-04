package br.com.pizzaplaza.product.services;

import br.com.pizzaplaza.entity.Category;
import br.com.pizzaplaza.entity.dtos.CategoryDto;
import br.com.pizzaplaza.product.repositories.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @Transactional
    public void update(CategoryDto dto) {
        if (dto.getOid() == null) {
            throw new IllegalArgumentException("Oid is required for update");
        }

        Category category = categoryRepository.findByOid(dto.getOid());

        category.setDescription(dto.getDescription());

        category = categoryRepository.update(category);
    }

    @Transactional
    public void delete(@NotBlank String oid) {

        Category category = categoryRepository.findByOid(oid);

        categoryRepository.delete(category);
    }

    public CategoryDto find(@NotBlank String oid) {

        Category category = categoryRepository.findByOid(oid);

        if (category == null) {
            throw new NotFoundException();
        }

        return new CategoryDto(category);
    }

    public Category findEntity(@NotBlank String oid) {

        Category category = categoryRepository.findByOid(oid);

        if (category == null) {
            throw new NotFoundException();
        }

        return category;
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
