package br.com.pizzaplaza.product.dtos;

import br.com.pizzaplaza.product.entities.Category;
import br.com.pizzaplaza.entity.fatherofall.OdinDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDto extends OdinDto {

    @NotBlank
    public String description;

    public CategoryDto() {}

    public CategoryDto(Category category) {
        setOid(category.getOid());
        setCreatedAt(category.getCreatedAt());
        setDescription(category.getDescription());
    }

    public Category toEntity() {
        Category category = new Category();

        category.setDescription(this.description);
        category.setOid(this.getOid());
        category.setCreatedAt(this.getCreatedAt());

        return category;
    }

    public Boolean isOidValid() {
        return getOid() != null && !getOid().isEmpty();
    }

    public Boolean isOidInvalid() {
        return getOid() == null || getOid().isEmpty();
    }
}
