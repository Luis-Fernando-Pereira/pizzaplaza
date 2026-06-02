package br.com.pizzaplaza.entity;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Person extends Odin {

    @Column(name = "cpf", length = 14)
    private String cpf;

    @Column(name = "name", length = 100, nullable = false)
    private String name;
}
