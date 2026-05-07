package br.com.pizzaplaza.entity;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "flavor")
public class Flavor extends Odin {

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "flavor")
    private List<FlavorCategory> categories;
}
