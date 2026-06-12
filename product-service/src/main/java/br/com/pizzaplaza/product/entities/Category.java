package br.com.pizzaplaza.product.entities;

import br.com.pizzaplaza.product.entities.fatherofall.Odin;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "category")
public class Category extends Odin {

    @Getter
    @Setter
    @Column(name = "description", length = 100, nullable = false)
    private String description;

}
