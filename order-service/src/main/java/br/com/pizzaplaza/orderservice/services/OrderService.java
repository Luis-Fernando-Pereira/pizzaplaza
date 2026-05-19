package br.com.pizzaplaza.orderservice.services;

import br.com.pizzaplaza.entity.Order;
import br.com.pizzaplaza.entity.Pizza;
import br.com.pizzaplaza.entity.PizzaFlavorSnapshot;
import br.com.pizzaplaza.entity.dtos.OrderDto;
import br.com.pizzaplaza.entity.dtos.PizzaDto;
import br.com.pizzaplaza.orderservice.integration.productservice.FlavorIntegrator;
import br.com.pizzaplaza.orderservice.repositories.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    @Inject
    FlavorIntegrator flavorIntegrator;

    @Transactional
    public Order createOrder(OrderDto dto) {
        validateOrder(dto);

        Order order = new Order();

        List<Pizza> pizzaList = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (PizzaDto pizzaDto : dto.getPizzas()) {

            Pizza pizza = new Pizza();
            pizza.setOrder(order);
            pizza.setSize(pizzaDto.getSize());

            List<PizzaFlavorSnapshot> snapshots = pizzaDto.getFlavorOids()
                    .stream()
                    .map(flavorIntegrator::find)
                    .map(flavor -> {
                        PizzaFlavorSnapshot snapshot = new PizzaFlavorSnapshot();

                        snapshot.setName(flavor.getName());
                        snapshot.setDescription(flavor.getDescription());
                        snapshot.setPrice(flavor.getPrice());
                        snapshot.setPizza(pizza);

                        return snapshot;
                    })
                    .toList();

            pizza.getFlavors().addAll(snapshots);

            BigDecimal pizzaPrice = calculatePizzaPrice(snapshots, pizzaDto.getFlavors().size());

            pizza.setUnitPrice(pizzaPrice);

            total = total.add(pizzaPrice);

            pizzaList.add(pizza);
        }

        order.getPizzaSet().addAll(pizzaList);
        order.setTotalPrice(total);

        orderRepository.persist(order);

        return order;
    }

    private void validateOrder(OrderDto dto) {
        if (dto == null || dto.getPizzas() == null || dto.getPizzas().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one pizza");
        }
    }

    private BigDecimal calculatePizzaPrice(List<PizzaFlavorSnapshot> flavors, Integer quantity) {
        BigDecimal highestFlavorPrice = flavors.stream()
                .map(PizzaFlavorSnapshot::getPrice)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        return highestFlavorPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
