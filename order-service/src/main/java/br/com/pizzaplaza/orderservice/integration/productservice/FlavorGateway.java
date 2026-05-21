package br.com.pizzaplaza.orderservice.integration.productservice;

import br.com.pizzaplaza.contracts.dtos.FlavorResponseDto;
import java.util.Set;

public interface FlavorGateway {

    FlavorResponseDto findByOid(String oid);
    Set<FlavorResponseDto> findByOids(Set<String> oids);

}
