package br.com.pizzaplaza.orderservice.dtos;

import br.com.pizzaplaza.entity.fatherofall.OdinDto;
import br.com.pizzaplaza.orderservice.entities.Order;
import br.com.pizzaplaza.orderservice.entities.Pizza;
import br.com.pizzaplaza.orderservice.enums.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderDto extends OdinDto {

    @NotNull
    private OrderStatus status;

    @NotNull
    @Size(min = 1)
    @Valid
    private Set<PizzaDto> pizzaList = new HashSet<>();

    @NotNull
    @Valid
    private CustumerDto costumer;

    @Positive
    private BigDecimal totalPrice;

    public OrderDto(){}

    public OrderDto(Order order) {
        setOid(order.getOid());
        setStatus(order.getStatus());
        setCostumer(new CustumerDto(order.getCustumer()));
        setTotalPrice(order.getTotalPrice());
        setPizzaList(order.getPizzaSet().stream().map(PizzaDto::new).collect(Collectors.toSet()));
    }

    public Order toEntity() {
        Order entity = new Order();

        entity.setCustumer(getCostumer().toEntity());
        entity.setTotalPrice(getTotalPrice());
        entity.setStatus(getStatus());
        pizzaList.forEach(dto -> {
            Pizza pizza = dto.toEntity();
            pizza.setOrder(entity);
            entity.getPizzaSet().add(pizza);
        });

        return entity;
    }
}
