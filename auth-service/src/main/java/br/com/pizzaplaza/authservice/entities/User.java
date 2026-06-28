package br.com.pizzaplaza.authservice.entities;

import br.com.pizzaplaza.authservice.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pizzaplaza_user")
public class User extends Person {

    public User() {}

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Getter
    @Setter
    @Column(nullable = false)
    private String password;

    @Getter
    @Setter
    @Column(nullable = false)
    private Boolean authenticated = false;

    @Getter
    @Setter
    @Column(name = "user_type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
