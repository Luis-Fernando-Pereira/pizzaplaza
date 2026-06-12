package br.com.pizzaplaza.orderservice.services;

import br.com.pizzaplaza.orderservice.dtos.OrderDto;
import br.com.pizzaplaza.orderservice.entities.Order;
import br.com.pizzaplaza.orderservice.repositories.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    @Transactional
    public OrderDto createOrder(OrderDto dto) {

        Order order = dto.toEntity();

        orderRepository.persist(order);

        dto.setOid(order.getOid());

        return dto;
    }

    @Transactional
    public OrderDto find(@NotBlank String oid) {
        Order order = orderRepository.findByOidOptional(oid)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        return new OrderDto(order);
    }

    @Transactional
    public List<OrderDto> findAll() {
        List<Order> orderList = orderRepository.listAll();

        if (orderList.isEmpty()) {
            return new ArrayList<>();
        }

        List<OrderDto> orderDtoList = new ArrayList<>();

        orderList.stream().map(OrderDto::new).forEach(orderDtoList::add);

        return orderDtoList;
    }
}
