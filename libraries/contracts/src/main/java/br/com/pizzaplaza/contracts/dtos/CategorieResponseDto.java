package br.com.pizzaplaza.contracts.dtos;

import lombok.Data;

@Data
public class CategorieResponseDto {
    private String oid;
    private String name;

    public CategorieResponseDto() {
    }

    public CategorieResponseDto(String oid, String name) {
        this.oid = oid;
        this.name = name;
    }
}
