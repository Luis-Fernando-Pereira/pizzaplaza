package br.com.pizzaplaza.orderservice.dtos;

import br.com.pizzaplaza.orderservice.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusEventDto {

    private String orderOid;
    private String userOid;
    private String newStatus;
    private String statusLabel;

    public static OrderStatusEventDto of(String orderOid, String userOid, OrderStatus status) {
        return new OrderStatusEventDto(orderOid, userOid, status.name(), statusLabel(status));
    }

    private static String statusLabel(OrderStatus status) {
        return switch (status) {
            case PREPARING         -> "Seu pedido está sendo preparado";
            case LEFT_FOR_DELIVERY -> "Seu pedido saiu para entrega";
            case DELIVERED         -> "Seu pedido foi entregue!";
            default                -> status.name();
        };
    }
}
