package br.com.pizzaplaza.product.libraries;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;

public class Product extends Odin {

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @JoinColumn(name = "category_oid", nullable = false)
    private Category category;
}
