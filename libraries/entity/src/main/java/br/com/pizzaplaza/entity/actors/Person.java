package br.com.pizzaplaza.entity.actors;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class Person extends Odin {

    @Getter
    @Setter
    @Column(unique = true)
    private String cpf;

    @Getter
    @Setter
    @Column
    private String name;

}
