package br.com.pizzaplaza.entity.actors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pizzaplaza_user")
public class User extends Person {

    public User(){}

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean authenticated;

    public String toString() {
        return "Usuário" + this.email + super.toString();
    }
}
