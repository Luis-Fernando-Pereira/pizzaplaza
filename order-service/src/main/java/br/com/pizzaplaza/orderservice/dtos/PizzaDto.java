package br.com.pizzaplaza.orderservice.dtos;

import br.com.pizzaplaza.entity.fatherofall.OdinDto;
import br.com.pizzaplaza.orderservice.entities.FlavorSnapshot;
import br.com.pizzaplaza.orderservice.entities.Pizza;
import br.com.pizzaplaza.orderservice.enums.PizzaSize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PizzaDto extends OdinDto {

    @NotNull
    private PizzaSize size;

    @NotNull
    @Size(min = 1, max = 4)
    @Valid
    private List<FlavorSnapshotDto> flavors;

    @NotNull
    @Positive
    private BigDecimal unitPrice;

    public PizzaDto(){}

    public PizzaDto(Pizza entity) {
        setSize(entity.getSize());
        setUnitPrice(entity.getUnitPrice());
        setFlavors(entity.getFlavors().stream().map(flavor -> new FlavorSnapshotDto(flavor)).toList());
        setOid(entity.getOid());
        setCreatedAt(entity.getCreatedAt());
    }

    public Pizza toEntity() {
        Pizza entity = new Pizza();

        entity.setSize(getSize());
        entity.setUnitPrice(getUnitPrice());
        getFlavors().forEach(flavorDto -> {
            FlavorSnapshot flavorSnapshot = flavorDto.toEntity();
            flavorSnapshot.setPizza(entity);
            entity.getFlavors().add(flavorSnapshot);
        });

        return entity;
    }

    public List<String> getFlavorOids() {
        return this.flavors.stream().map(FlavorSnapshotDto::getOid).toList();
    }
}
