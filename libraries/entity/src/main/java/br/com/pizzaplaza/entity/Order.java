package br.com.pizzaplaza.entity;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "order")
public class Order extends Odin {

    @OneToMany(mappedBy = "order")
    private Set<Pizza> pizzaSet = new HashSet<>();
}
