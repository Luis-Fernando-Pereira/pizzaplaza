package br.com.pizzaplaza.product.libraries;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "flavor")
public class Flavor extends Odin {

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "flavor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlavorCategory> categories = new ArrayList<>();;
}
