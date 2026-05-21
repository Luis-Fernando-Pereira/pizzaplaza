package br.com.pizzaplaza.orderservice.dtos;

import br.com.pizzaplaza.entity.fatherofall.OdinDto;
import br.com.pizzaplaza.orderservice.entities.Custumer;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CostumerDto extends OdinDto {

    private String userOid;

    @NotBlank
    private String name;

    private String cpf;

    public Custumer toEntity() {
        Custumer custumer = new Custumer();

        custumer.setCpf(getCpf());
        custumer.setName(getName());
        custumer.setUserOid(getUserOid());

        return custumer;
    }
}
