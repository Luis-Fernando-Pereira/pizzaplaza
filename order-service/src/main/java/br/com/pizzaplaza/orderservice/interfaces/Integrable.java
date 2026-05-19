package br.com.pizzaplaza.orderservice.interfaces;

public interface Integrable<T> {
    T find(String oid);
}
