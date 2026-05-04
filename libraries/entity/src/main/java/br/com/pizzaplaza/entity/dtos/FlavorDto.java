package br.com.pizzaplaza.entity.dtos;

import br.com.pizzaplaza.entity.Flavor;
import br.com.pizzaplaza.entity.fatherofall.OdinDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class FlavorDto extends OdinDto {

    private String oid;

    @NotBlank
    private String name;

    private Date createdAt;

    @NotNull
    private CategoryDto category;

    public FlavorDto(){}

    public FlavorDto(@NotNull Flavor flavor) {
        setCategory(new CategoryDto(flavor.getCategory()));
        setName(flavor.getName());
        setOid(flavor.getOid());
        setCreatedAt(flavor.getCreatedAt());
    }

    public Flavor toEntity() {
        Flavor entity = new Flavor();

        entity.setCategory(this.category.toEntity());
        entity.setName(this.name);

        return entity;
    }

}
