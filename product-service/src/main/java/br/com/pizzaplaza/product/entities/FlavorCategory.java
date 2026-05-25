package br.com.pizzaplaza.product.entities;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Optional;

@Data
@Entity
@Table(name = "flavor_category")
public class FlavorCategory extends Odin {

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "flavor_oid", nullable = false)
    private Flavor flavor;

    @ManyToOne
    @JoinColumn(name = "category_oid", nullable = false)
    private Category category;

    public String getCategoryOid() {
        return Optional.ofNullable(category).get().getOid();
    }

}
