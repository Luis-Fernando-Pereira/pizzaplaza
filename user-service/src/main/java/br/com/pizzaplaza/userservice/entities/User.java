package br.com.pizzaplaza.userservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pizzaplaza_user")
public class User extends Person {

    public User() {}

    @Getter @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Getter @Setter
    @Column(nullable = false)
    private String password;

    @Getter @Setter
    @Column(nullable = false)
    private Boolean authenticated = false;
}
