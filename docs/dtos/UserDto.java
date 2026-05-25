package br.com.pizzaplaza.entity.dtos;

import br.com.pizzaplaza.entity.fatherofall.OdinDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class UserDto extends OdinDto {

    @NotNull(message = "Nome é obrigatório")
    @NotBlank(message = "Nome é obrigatório")
    public String name;

    @NotNull(message = "CPF é obrigatório")
    @NotBlank(message = "CPF é obrigatório")
    @CPF
    public String cpf;

    @Email(message = "Email inválido")
    public String email;

    @Size(message = "Senha deve ter no mínimo 18 caracteres", min = 18)
    public String password;
    public Type userType;

    public enum Type {ADMIN, CLIENT, SELLER}
}
