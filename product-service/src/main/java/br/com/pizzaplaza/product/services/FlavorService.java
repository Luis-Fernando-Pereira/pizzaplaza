package br.com.pizzaplaza.product.services;

import br.com.pizzaplaza.entity.Category;
import br.com.pizzaplaza.entity.Flavor;
import br.com.pizzaplaza.entity.FlavorCategory;
import br.com.pizzaplaza.entity.dtos.FlavorDto;
import br.com.pizzaplaza.product.factories.FlavorCategoryFactory;
import br.com.pizzaplaza.product.repositories.FlavorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class FlavorService {

    @Inject
    FlavorRepository flavorRepository;

    @Inject
    CategoryService categoryService;

    @Transactional
    public FlavorDto save(@Valid FlavorDto dto) {
        if (dto.isCategoryListInvalid()) {
            throw new IllegalArgumentException("All categories must have a valid oid");
        }

        Flavor flavor = dto.toEntity();

        List<Category> categories = loadCategories(dto);

        createRelations(categories, flavor);

        flavorRepository.save(flavor);

        dto.setOid(flavor.getOid());
        dto.setCreatedAt(flavor.getCreatedAt());

        return dto;
    }

    private void createRelations(List<Category> categories, Flavor flavor) {
        categories.forEach(category -> {
            FlavorCategory flavorCategory = FlavorCategoryFactory.createFlavorCategory(flavor, category);
            flavor.getCategories().add(flavorCategory);
        });
    }

    private List<Category> loadCategories(FlavorDto dto) {
        return categoryService.findByFlavorCategoryList(dto.getCategories());
    }

    @Transactional
    public void update(FlavorDto dto) {

        validateUpdate(dto);

        Flavor flavor = findFlavor(dto.getOid());

        List<Category> categories = loadCategories(dto);

        syncCategories(flavor, categories);

        updateFlavorData(flavor, dto);

        flavorRepository.update(flavor);
    }

    private void validateUpdate(FlavorDto dto) {

        if (dto.getOid() == null) {
            throw new IllegalArgumentException("Oid is required for update");
        }

        if (dto.isCategoryListInvalid()) {
            throw new IllegalArgumentException("All categories must have a valid oid");
        }
    }

    private Flavor findFlavor(String oid) {

        Flavor flavor = flavorRepository.findByOid(oid);

        if (flavor == null) {
            throw new IllegalArgumentException("Flavor not found");
        }

        return flavor;
    }

    private void syncCategories(Flavor flavor, List<Category> categories) {

        Set<String> categoryOids = extractCategoryOids(categories);

        removeUnusedCategories(flavor, categoryOids);

        addMissingCategories(flavor, categories);
    }

    private Set<String> extractCategoryOids(List<Category> categories) {
        return categories.stream()
                .map(Category::getOid)
                .collect(Collectors.toSet());
    }

    private void removeUnusedCategories(Flavor flavor, Set<String> categoryOids) {

        flavor.getCategories().removeIf(flavorCategory ->
                !categoryOids.contains(
                        flavorCategory.getCategory().getOid()
                )
        );
    }

    private void addMissingCategories(Flavor flavor, List<Category> categories) {

        Set<String> existingCategoryOids = flavor.getCategories()
                .stream()
                .map(flavorCategory -> flavorCategory.getCategory().getOid())
                .collect(Collectors.toSet());

        categories.forEach(category -> {

            if (existingCategoryOids.contains(category.getOid())) {
                return;
            }

            flavor.getCategories().add(FlavorCategoryFactory.createFlavorCategory(flavor, category));
        });
    }

    private void updateFlavorData(Flavor flavor, FlavorDto dto) {
        flavor.setName(dto.getName());
        flavor.setPrice(dto.getPrice());
    }

    @Transactional
    public void delete(@NotBlank String oid) {
        Flavor flavor = flavorRepository.findByOid(oid);

        flavorRepository.delete(flavor);
    }

    public FlavorDto find(String oid) {

        Flavor flavor = findEntity(oid);

        return new FlavorDto(flavor);
    }

    public Flavor findEntity(@NotNull String oid) {
        return flavorRepository.findByOid(oid);
    }

    public List<FlavorDto> findAll() {

        List<Flavor> results = flavorRepository.findAll();

        if (results == null) {
            return new ArrayList<>();
        }

        return results.stream().map(FlavorDto::new).toList();
    }

}
