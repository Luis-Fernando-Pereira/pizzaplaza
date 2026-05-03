package br.com.pizzaplaza.entity.actors;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="admin")
public class Admin extends Odin {

    @OneToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
}
