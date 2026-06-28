package br.com.pizzaplaza.authservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDto {

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    public String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(message = "Senha deve ter no mínimo 8 caracteres", min = 8)
    public String password;
}
