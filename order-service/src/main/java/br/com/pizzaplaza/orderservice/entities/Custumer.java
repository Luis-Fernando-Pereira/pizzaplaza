package br.com.pizzaplaza.orderservice.entities;

import br.com.pizzaplaza.entity.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "costumer")
public class Custumer extends Person {

    @Getter
    @Setter
    @Column(name = "user_oid", length = 38)
    private String userOid;
}
