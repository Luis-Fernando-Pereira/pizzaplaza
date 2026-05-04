package br.com.pizzaplaza.entity;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pizza_flavor")
public class PizzaFlavor extends Odin  {

    @ManyToOne(fetch = FetchType.LAZY)
    private Pizza pizza;

    @ManyToOne(fetch = FetchType.LAZY)
    private Flavor flavor;
}
