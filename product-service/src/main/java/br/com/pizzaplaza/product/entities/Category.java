package br.com.pizzaplaza.product.entities;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "category")
public class Category extends Odin {

    @Column(name = "description", length = 100, nullable = false)
    private String description;

}
