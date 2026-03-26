package com.example.checkout.model;

import java.util.List;

public class CartRequest {
    public String cartId;
    public List<CartItem> items;
    public String paymentMethod;
    public ShippingAddress shippingAddress;
}