package br.com.pizzaplaza.userservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "admin")
public class Admin extends Odin {

    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "user_oid", nullable = false)
    private User user;
}
