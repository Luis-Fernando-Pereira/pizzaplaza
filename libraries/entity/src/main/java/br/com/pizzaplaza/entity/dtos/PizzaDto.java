package br.com.pizzaplaza.entity.dtos;

import br.com.pizzaplaza.entity.Pizza;
import br.com.pizzaplaza.entity.PizzaFlavor;
import br.com.pizzaplaza.entity.enums.PizzaSize;
import br.com.pizzaplaza.entity.fatherofall.OdinDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class PizzaDto extends OdinDto {

    @NotBlank
    private PizzaSize size;

    @NotEmpty
    private List<FlavorDto> flavors;

    public PizzaDto(){};

    public PizzaDto(Pizza entity) {
        setSize(entity.getSize());
        setFlavors(entity.getFlavors().stream().map(rel -> new FlavorDto(rel.getFlavor())).toList());
        setOid(entity.getOid());
        setCreatedAt(entity.getCreatedAt());
    }

    public Pizza toEntity() {
        Pizza entity = new Pizza();

        entity.setSize(this.size);

        this.flavors.forEach(flavorDto -> {
            PizzaFlavor rel = new PizzaFlavor();
            rel.setFlavor(flavorDto.toEntity());
            rel.setPizza(entity);
            entity.getFlavors().add(rel);
        });

        return entity;
    }
}
