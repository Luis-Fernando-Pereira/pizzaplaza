package br.com.pizzaplaza.entity;

import br.com.pizzaplaza.entity.enums.OrderStatus;
import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
    private OrderStatus status = OrderStatus.RECEIVED;

    @Getter
    @Setter
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Getter
    @OneToMany(mappedBy = "order")
    private Set<Pizza> pizzaSet = new HashSet<>();
}
