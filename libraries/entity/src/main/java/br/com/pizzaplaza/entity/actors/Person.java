package br.com.pizzaplaza.entity.actors;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class Person extends Odin {

    @Column(unique = true)
    public String cpf;

    @Column
    public String name;

}
