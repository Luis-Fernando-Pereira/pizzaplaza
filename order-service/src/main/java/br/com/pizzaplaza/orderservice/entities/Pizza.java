package br.com.pizzaplaza.orderservice.entities;

import br.com.pizzaplaza.orderservice.enums.PizzaSize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pizza")
public class Pizza extends Odin {

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "size", length = 10, nullable = false)
    private PizzaSize size;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "order_oid", nullable = false)
    private Order order;

    @Getter
    @Setter
    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Getter
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pizza", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FlavorSnapshot> flavors = new HashSet<>();

}
