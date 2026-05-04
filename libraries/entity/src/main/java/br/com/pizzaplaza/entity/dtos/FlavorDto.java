package br.com.pizzaplaza.entity.dtos;

import br.com.pizzaplaza.entity.fatherofall.OdinDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FlavorDto extends OdinDto {

    private String oid;

    @NotBlank
    private String name;

    private Data createdAt;

    @NotNull
    private CategoryDto category;

}
