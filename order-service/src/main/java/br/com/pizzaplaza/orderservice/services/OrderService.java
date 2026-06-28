package br.com.pizzaplaza.orderservice.services;

import br.com.pizzaplaza.orderservice.dtos.OrderDto;
import br.com.pizzaplaza.orderservice.dtos.OrderStatusEventDto;
import br.com.pizzaplaza.orderservice.entities.Order;
import br.com.pizzaplaza.orderservice.enums.OrderStatus;
import br.com.pizzaplaza.orderservice.repositories.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class OrderService {

    private static final Map<OrderStatus, OrderStatus> NEXT_STATUS = Map.of(
            OrderStatus.RECEIVED,          OrderStatus.PREPARING,
            OrderStatus.PREPARING,         OrderStatus.LEFT_FOR_DELIVERY,
            OrderStatus.LEFT_FOR_DELIVERY, OrderStatus.DELIVERED
    );

    @Inject
    OrderRepository orderRepository;

    @Inject
    OrderStatusNotifier notifier;

    @Inject
    JsonWebToken jwt;

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
        return orderList.stream().map(OrderDto::new).toList();
    }

    @Transactional
    public List<OrderDto> findByCurrentUser() {
        String userOid = jwt.getSubject();
        return orderRepository.findByUserOid(userOid)
                .stream().map(OrderDto::new).toList();
    }

    @Transactional
    public OrderDto advanceStatus(@NotBlank String oid) {
        Order order = orderRepository.findByOidOptional(oid)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado"));

        OrderStatus current = order.getStatus();
        OrderStatus next    = NEXT_STATUS.get(current);

        if (next == null) {
            throw new BadRequestException("Pedido já foi entregue e não pode ser avançado");
        }

        order.setStatus(next);

        String userOid = order.getCustumer().getUserOid();
        notifier.emit(OrderStatusEventDto.of(oid, userOid, next));

        return new OrderDto(order);
    }
}
