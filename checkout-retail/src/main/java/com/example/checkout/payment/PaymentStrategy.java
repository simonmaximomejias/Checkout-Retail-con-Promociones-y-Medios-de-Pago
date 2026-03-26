package com.example.checkout.payment;

import com.example.checkout.model.DiscountDetail;

public interface PaymentStrategy {
    DiscountDetail apply(double amount);
}