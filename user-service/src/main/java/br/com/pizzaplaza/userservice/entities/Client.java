package br.com.pizzaplaza.userservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "client")
public class Client extends Odin {

    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "user_oid", nullable = false)
    private User user;
}
