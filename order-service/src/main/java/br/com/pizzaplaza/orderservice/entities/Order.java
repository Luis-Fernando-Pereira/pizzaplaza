package br.com.pizzaplaza.orderservice.entities;

import br.com.pizzaplaza.orderservice.enums.OrderStatus;
import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order")
public class Order extends Odin {

    @Getter
    @Setter
    @Column(name = "status", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.RECEIVED;

    @Getter
    @Setter
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Getter
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Pizza> pizzaSet = new HashSet<>();

    @Getter
    @Setter
    @JoinColumn(name = "costumer_oid")
    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Custumer custumer;
}
