package br.com.pizzaplaza.entity.dtos;

import br.com.pizzaplaza.entity.enums.OrderStatus;
import br.com.pizzaplaza.entity.fatherofall.OdinDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto extends OdinDto {

    @NotBlank
    private OrderStatus status;

    @NotNull
    private List<PizzaDto> pizzas;
}
