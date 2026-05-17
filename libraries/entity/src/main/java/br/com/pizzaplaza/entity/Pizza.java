package br.com.pizzaplaza.entity;

import br.com.pizzaplaza.entity.enums.PizzaSize;
import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pizza")
public class Pizza extends Odin {

    @Getter
    @Setter
    @Column(name = "size", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private PizzaSize size;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "order_oid")
    private Order order;

    @Getter
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pizza", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PizzaFlavor> flavors = new HashSet<>();

}
