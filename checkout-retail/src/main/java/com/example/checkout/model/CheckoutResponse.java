package com.example.checkout.model;

import java.util.List;

public class CheckoutResponse {
    public double productsTotal;
    public double productDiscounts;
    public double subtotal;
    public double shippingCost;
    public double paymentDiscount;
    public double total;
    public List<DiscountDetail> discounts;
}