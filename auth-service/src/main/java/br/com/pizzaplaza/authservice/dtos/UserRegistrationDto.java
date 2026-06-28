package br.com.pizzaplaza.authservice.dtos;

import br.com.pizzaplaza.authservice.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegistrationDto {

    @NotBlank(message = "Nome é obrigatório")
    public String name;

    @NotBlank(message = "CPF é obrigatório")
    public String cpf;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    public String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(message = "Senha deve ter no mínimo 8 caracteres", min = 8)
    public String password;

    @NotNull(message = "Tipo de usuário é obrigatório")
    public UserType userType;
}
