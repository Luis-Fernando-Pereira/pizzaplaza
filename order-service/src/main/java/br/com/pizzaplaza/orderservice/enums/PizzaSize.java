package br.com.pizzaplaza.orderservice.enums;

import java.math.BigDecimal;

public enum PizzaSize {
    SMALL (new BigDecimal("0.70")),
    MEDIUM(new BigDecimal("1.00")),
    LARGE (new BigDecimal("1.40"));

    public final BigDecimal multiplier;

    PizzaSize(BigDecimal multiplier) {
        this.multiplier = multiplier;
    }
}
