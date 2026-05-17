package br.com.pizzaplaza.entity;

import br.com.pizzaplaza.entity.fatherofall.Odin;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order")
public class Order extends Odin {

}
