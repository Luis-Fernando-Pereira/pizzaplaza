package br.com.pizzaplaza.orderservice.services;

import br.com.pizzaplaza.orderservice.dtos.OrderDto;
import br.com.pizzaplaza.orderservice.dtos.PizzaDto;
import br.com.pizzaplaza.orderservice.entities.FlavorSnapshot;
import br.com.pizzaplaza.orderservice.entities.Order;
import br.com.pizzaplaza.orderservice.entities.Pizza;
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

    @Transactional
    public OrderDto createOrder(OrderDto dto) {

        Order order = dto.toEntity();

        BigDecimal total = BigDecimal.ZERO;


    }

    @Transactional
    public Order find(String oid) {
        if (oid == null) {
            throw new IllegalArgumentException("Oid is required");
        }

        return orderRepository.findByOidOptional(oid)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    @Transactional
    public List<Order> findAll() {
        return orderRepository.listAll();
    }


    private BigDecimal calculatePizzaPrice(List<FlavorSnapshot> flavors, Integer quantity) {
        BigDecimal highestFlavorPrice = flavors.stream()
                .map(FlavorSnapshot::getPrice)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        return highestFlavorPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
