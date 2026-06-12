package br.com.pizzaplaza.orderservice.dtos;

import br.com.pizzaplaza.orderservice.entities.Custumer;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustumerDto extends OdinDto {

    private String userOid;

    @NotBlank
    private String name;

    private String cpf;

    public CustumerDto(){}

    public CustumerDto(Custumer custumer){
        setCpf(custumer.getCpf());
        setUserOid(custumer.getUserOid());
        setName(custumer.getName());
        setCreatedAt(custumer.getCreatedAt());
        setOid(custumer.getOid());
    }

    public Custumer toEntity() {
        Custumer custumer = new Custumer();

        String c = getCpf();
        custumer.setCpf(c);
        custumer.setName(getName());
        custumer.setUserOid(getUserOid());

        return custumer;
    }
}
