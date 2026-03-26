package com.example.checkout;

import com.example.checkout.model.*;
import com.example.checkout.service.CheckoutService;

import java.util.ArrayList;
import java.util.List;

public class CheckoutApplication {
    public static void main(String[] args) {

        CheckoutService service = new CheckoutService();

        // Crear carrito de prueba
        List<CartItem> items = new ArrayList<>();
        items.add(new CartItem("p-001", 1)); // 1 Laptop
        items.add(new CartItem("p-010", 2)); // 2 Mouse

        CartRequest request = new CartRequest();
        request.items = items;
        request.paymentMethod = "DEBIT";

        // Ejecutar checkout
        CheckoutResponse response = service.checkout(request);

        // Mostrar resultados
        System.out.println("Subtotal productos: " + response.productsTotal);
        System.out.println("Descuento productos: " + response.productDiscounts);
        System.out.println("Subtotal después de descuentos: " + response.subtotal);
        System.out.println("Costo de envío: " + response.shippingCost);
        System.out.println("Descuento pago: " + response.paymentDiscount);
        System.out.println("Total a pagar: " + response.total);
    }
}