package com.example.checkout.payment;

import com.example.checkout.model.DiscountDetail;

public class DebitPayment implements PaymentStrategy {

    public DiscountDetail apply(double amount) {
        double discount = amount * 0.10;
        return new DiscountDetail("PAYMENT", "10% débito", discount);
    }
}