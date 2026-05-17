package br.com.pizzaplaza.entity.fatherofall;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order")
public class Order extends Odin{

}
