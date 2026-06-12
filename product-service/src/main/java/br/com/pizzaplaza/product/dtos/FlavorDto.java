package br.com.pizzaplaza.product.dtos;

import br.com.pizzaplaza.product.entities.Flavor;
import br.com.pizzaplaza.product.entities.FlavorCategory;
import br.com.pizzaplaza.product.entities.fatherofall.OdinDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FlavorDto extends OdinDto {

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal price;

    @NotBlank
    private String description;

    @NotNull
    private List<CategoryDto> categories;

    public FlavorDto(){}

    public FlavorDto(@NotNull Flavor flavor) {
        setPrice(flavor.getPrice());
        setName(flavor.getName());
        setDescription(flavor.getDescription());
        setOid(flavor.getOid());
        setCreatedAt(flavor.getCreatedAt());
        setCategories(flavor.getCategories().stream().map(category -> new CategoryDto(category.getCategory())).toList());
    }

    public Flavor toEntity() {
        Flavor entity = new Flavor();

        entity.setName(getName());
        entity.setPrice(getPrice());
        entity.setDescription(getDescription());

        return entity;
    }

    @JsonIgnore
    public Boolean isCategoryListInvalid() {
        return categories.stream().filter(CategoryDto::isOidInvalid).findFirst().isPresent();
    }

}
