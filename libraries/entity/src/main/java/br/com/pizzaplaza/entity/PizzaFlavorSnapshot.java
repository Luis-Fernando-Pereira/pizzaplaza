package br.com.pizzaplaza.entity;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "pizza_flavor_snapshot")
public class PizzaFlavorSnapshot extends Odin  {

    @Getter
    @Setter
    @Column(name = "flavor_oid")
    private String flavorOid;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "description")
    private String description;

    @Getter
    @Setter
    @Column(name = "price")
    private BigDecimal price;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "pizza_oid")
    private Pizza pizza;

}
