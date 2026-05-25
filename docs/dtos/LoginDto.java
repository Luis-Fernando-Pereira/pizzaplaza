package br.com.pizzaplaza.entity.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class LoginDto {

    @Email(message = "Email inválido")
    public String email;

    @Size(message = "Senha deve ter no mínimo 18 caracteres", min = 18)
    public String password;
}
