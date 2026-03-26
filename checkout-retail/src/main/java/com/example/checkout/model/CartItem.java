package com.example.checkout.model;

public class CartItem {
    public String sku;
    public int quantity;

    // Constructor vacío (ya existente)
    public CartItem() {}

    // ✅ Nuevo constructor con parámetros
    public CartItem(String sku, int quantity) {
        this.sku = sku;
        this.quantity = quantity;
    }
}