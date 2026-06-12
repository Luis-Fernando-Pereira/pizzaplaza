package br.com.pizzaplaza.product.entities;

import br.com.pizzaplaza.product.entities.fatherofall.Odin;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Entity
@Table(name = "flavor_category")
public class FlavorCategory extends Odin {

    @Getter
    @Setter
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "flavor_oid", nullable = false)
    private Flavor flavor;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "category_oid", nullable = false)
    private Category category;

    public String getCategoryOid() {
        return Optional.ofNullable(category).get().getOid();
    }

}
