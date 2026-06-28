package br.com.pizzaplaza.userservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class Person extends Odin {

    @Getter @Setter
    @Column(name = "cpf", length = 14, unique = true)
    private String cpf;

    @Getter @Setter
    @Column(name = "name", length = 100, nullable = false)
    private String name;
}
