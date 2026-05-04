package br.com.pizzaplaza.entity.dtos;

import br.com.pizzaplaza.entity.fatherofall.OdinDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDto extends OdinDto {

    @NotBlank
    public String name;

    @NotNull
    public int quantity;

    @NotNull
    public CategoryDto categoryDto;
}
