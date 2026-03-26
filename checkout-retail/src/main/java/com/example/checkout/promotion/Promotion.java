package com.example.checkout.promotion;

import com.example.checkout.model.CartRequest;
import com.example.checkout.model.DiscountDetail;
import com.example.checkout.model.Product;
import java.util.Map;

public interface Promotion {
    DiscountDetail apply(CartRequest request); // método original
    DiscountDetail apply(CartRequest request, Map<String, Product> productCatalog); // nuevo método
}