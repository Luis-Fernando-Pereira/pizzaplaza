package br.com.pizzaplaza.entity.dtos;

import br.com.pizzaplaza.entity.Category;
import br.com.pizzaplaza.entity.fatherofall.OdinDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
}
