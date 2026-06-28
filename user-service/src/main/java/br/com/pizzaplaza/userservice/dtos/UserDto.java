package br.com.pizzaplaza.userservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@Data
public class UserDto {

    public String oid;
    public Date createdAt;

    @NotNull(message = "Nome é obrigatório")
    @NotBlank(message = "Nome é obrigatório")
    public String name;

    @NotNull(message = "CPF é obrigatório")
    @NotBlank(message = "CPF é obrigatório")
    @CPF
    public String cpf;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    public String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(message = "Senha deve ter no mínimo 8 caracteres", min = 8)
    public String password;

    public Type userType;

    public enum Type {
        ADMIN, CLIENT, SELLER
    }
}
