package br.com.pizzaplaza.entity;

import br.com.pizzaplaza.entity.enums.PizzaSize;
import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "pizza")
public class Pizza extends Odin {

    @Column(name = "size", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private PizzaSize size;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pizza", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PizzaFlavor> flavors;

}
