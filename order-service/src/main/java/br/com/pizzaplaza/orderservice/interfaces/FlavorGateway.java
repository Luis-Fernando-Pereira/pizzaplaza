package br.com.pizzaplaza.orderservice.interfaces;

import br.com.pizzaplaza.entity.dtos.FlavorDto;

public interface FlavorGateway {
    FlavorDto find(String oid);
}
