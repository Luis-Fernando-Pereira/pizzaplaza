package br.com.pizzaplaza.orderservice.entities;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import br.com.pizzaplaza.orderservice.dtos.FlavorSnapshotDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "pizza_flavor_snapshot")
public class FlavorSnapshot extends Odin  {

    @Getter
    @Setter
    @Column(name = "flavor_oid", nullable = false)
    private String flavorOid;

    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "description")
    private String description;

    @Getter
    @Setter
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "pizza_oid", nullable = false)
    private Pizza pizza;

}
