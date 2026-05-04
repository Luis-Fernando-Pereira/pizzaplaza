package br.com.pizzaplaza.entity.dtos;

import br.com.pizzaplaza.entity.fatherofall.OdinDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CategoryDto extends OdinDto {

    @NotEmpty(message = "Description cannot be empty")
    @NotBlank(message = "Description cannot be blank")
    @NotNull(message = "Description cannot be null")
    public String description;
}
