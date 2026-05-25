package br.com.pizzaplaza.entity.actors;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "seller")
public class Seller extends Odin {

    @OneToOne
    @JoinColumn(name="user_oid",nullable = false)
    private User user;

}
