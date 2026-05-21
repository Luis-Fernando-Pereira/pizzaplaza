package br.com.pizzaplaza.orderservice.dtos;

import br.com.pizzaplaza.entity.fatherofall.OdinDto;
import br.com.pizzaplaza.orderservice.entities.FlavorSnapshot;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class FlavorSnapshotDto extends OdinDto {

    @NotBlank
    private String flavorOid;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    public FlavorSnapshotDto(){};

    public FlavorSnapshotDto(FlavorSnapshot entity) {
        setOid(entity.getOid());
        setCreatedAt(entity.getCreatedAt());
        setFlavorOid(entity.getFlavorOid());
        setName(entity.getName());
        setDescription(entity.getDescription());
        setPrice(entity.getPrice());
    }

    public FlavorSnapshot toEntity() {
        FlavorSnapshot flavorSnapshot = new FlavorSnapshot();

        flavorSnapshot.setFlavorOid(getFlavorOid());
        flavorSnapshot.setName(getName());
        flavorSnapshot.setDescription(getDescription());
        flavorSnapshot.setPrice(getPrice());

        return flavorSnapshot;
    }
}
