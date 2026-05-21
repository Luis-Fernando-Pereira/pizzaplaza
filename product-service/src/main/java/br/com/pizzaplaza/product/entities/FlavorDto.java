package br.com.pizzaplaza.product.libraries;

import br.com.pizzaplaza.entity.Flavor;
import br.com.pizzaplaza.entity.FlavorCategory;
import br.com.pizzaplaza.entity.fatherofall.OdinDto;
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
        setOid(flavor.getOid());
        setCreatedAt(flavor.getCreatedAt());
        setCategories(flavor.getCategories().stream().map(category -> new CategoryDto(category.getCategory())).toList());
    }

    public Flavor toEntity() {
        Flavor entity = new Flavor();

        entity.setName(getName());
        entity.setPrice(getPrice());

        return entity;
    }

    public Boolean isCategoryListInvalid() {
        return categories.stream().filter(CategoryDto::isOidInvalid).findFirst().isPresent();
    }

}
