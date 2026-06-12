package br.com.pizzaplaza.orderservice.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class OdinDto {
    public String oid;
    public Date createdAt;
}
