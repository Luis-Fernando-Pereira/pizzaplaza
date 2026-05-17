package br.com.pizzaplaza.entity;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order")
public class Order extends Odin {

    @Getter
    @OneToMany(mappedBy = "order")
    private Set<Pizza> pizzaSet = new HashSet<>();
}
