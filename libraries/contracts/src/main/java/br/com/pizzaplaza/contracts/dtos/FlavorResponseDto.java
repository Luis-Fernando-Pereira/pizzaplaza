package br.com.pizzaplaza.contracts.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class FlavorResponseDto {
    private String name;
    private String description;
    private BigDecimal price;

    Set<CategorieResponseDto> categories = new HashSet<>();
}
