package br.com.pizzaplaza.entity.actors;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "client")
public class Client extends Odin {

    @OneToOne
    @JoinColumn(name="user_oid")
    public User user;

}
