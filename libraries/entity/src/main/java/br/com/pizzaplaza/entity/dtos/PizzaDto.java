package br.com.pizzaplaza.entity.dtos;

import br.com.pizzaplaza.entity.enums.PizzaSize;
import br.com.pizzaplaza.entity.fatherofall.OdinDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class PizzaDto extends OdinDto {

    @NotBlank
    private PizzaSize size;

    @NotEmpty
    private List<FlavorDto> flavors;
}
