package br.com.pizzaplaza.product.entities;

import br.com.pizzaplaza.product.entities.fatherofall.Odin;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flavor")
public class Flavor extends Odin {

    @Getter
    @Setter
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Getter
    @Setter
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Getter
    @Setter
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "flavor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlavorCategory> categories = new ArrayList<>();;
}
