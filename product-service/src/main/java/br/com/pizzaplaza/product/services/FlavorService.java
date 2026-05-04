package br.com.pizzaplaza.product.services;

import br.com.pizzaplaza.entity.Category;
import br.com.pizzaplaza.entity.Flavor;
import br.com.pizzaplaza.entity.dtos.CategoryDto;
import br.com.pizzaplaza.entity.dtos.FlavorDto;
import br.com.pizzaplaza.product.repositories.FlavorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FlavorService {

    @Inject
    FlavorRepository flavorRepository;

    @Inject
    CategoryService categoryService;

    @Transactional
    public FlavorDto save(FlavorDto dto) {
        Flavor flavor = dto.toEntity();

        flavorRepository.save(flavor);

        dto.setOid(flavor.getOid());
        dto.setCreatedAt(flavor.getCreatedAt());

        return dto;
    }

    @Transactional
    public void update(FlavorDto dto) {
        if (dto.getOid() == null) {
            throw new IllegalArgumentException("Oid is required for update");
        }

        Flavor flavor = flavorRepository.findByOid(dto.getOid());

        flavor.setName(dto.getName());

        flavor.setCategory(categoryService.findEntity(dto.getCategory().getOid()));

        flavor = flavorRepository.update(flavor);
    }

    @Transactional
    public void delete(@NotBlank String oid) {
        Flavor flavor = flavorRepository.findByOid(oid);

        flavorRepository.delete(flavor);
    }

    public FlavorDto find(String oid) {

        Flavor flavor = flavorRepository.findByOid(oid);

        return new FlavorDto(flavor);
    }

    public List<FlavorDto> findAll() {

        List<Flavor> results = flavorRepository.findAll();

        if (results == null) {
            return new ArrayList<>();
        }

        return results.stream().map(FlavorDto::new).toList();
    }

}
