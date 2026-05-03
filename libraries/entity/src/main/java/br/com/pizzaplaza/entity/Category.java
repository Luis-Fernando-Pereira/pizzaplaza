package br.com.pizzaplaza.entity;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.Column;

public class Category extends Odin {

    @Column(name = "description", length = 100, nullable = false)
    private String description;

}
