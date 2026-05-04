package br.com.pizzaplaza.entity;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "flavor")
public class Flavor extends Odin {

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_oid")
    private Category category;
}
