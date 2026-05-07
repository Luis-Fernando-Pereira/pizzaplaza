package br.com.pizzaplaza.entity.dtos;

import br.com.pizzaplaza.entity.Flavor;
import br.com.pizzaplaza.entity.fatherofall.OdinDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class FlavorDto extends OdinDto {

    @NotBlank
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private List<CategoryDto> categories;

    public FlavorDto(){}

    public FlavorDto(@NotNull Flavor flavor) {
        setPrice(flavor.getPrice());
        setName(flavor.getName());
        setOid(flavor.getOid());
        setCreatedAt(flavor.getCreatedAt());
    }

    public Flavor toEntity() {
        Flavor entity = new Flavor();

        entity.setName(getName());
        entity.setPrice(getPrice());

        return entity;
    }

}
