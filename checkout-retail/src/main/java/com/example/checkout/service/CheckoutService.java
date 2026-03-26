package com.example.checkout.service;

import com.example.checkout.model.*;
import com.example.checkout.promotion.*;
import com.example.checkout.payment.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CheckoutService {

    // Catálogo interno de productos (SKU -> Product)
    private static final Map<String, Product> productCatalog = new HashMap<>();
    static {
        productCatalog.put("p-001", new Product("p-001", "Laptop", 100000));
        productCatalog.put("p-010", new Product("p-010", "Mouse", 50000));
        productCatalog.put("p-003", new Product("p-003", "Teclado", 30000));
        // Puedes agregar más productos según sea necesario
    }

    public CheckoutResponse checkout(CartRequest request) {

        // 1️⃣ Calcular el total de productos
        double productsTotal = 0;
        for (CartItem item : request.items) {
            Product p = productCatalog.get(item.sku);
            if (p != null) {
                productsTotal += p.getPrice() * item.quantity;
            }
        }

        // 2️⃣ Aplicar promociones
        Promotion promo = new BulkDiscountPromotion();
        DiscountDetail productDiscount = promo.apply(request, productCatalog); // Se usa el método con catálogo

        double subtotal = productsTotal - productDiscount.getAmount();

        // 3️⃣ Calcular costo de envío
        double shipping = subtotal > 100000 ? 0 : 5000;

        // 4️⃣ Aplicar descuento por medio de pago
        PaymentStrategy payment = null;
        if ("DEBIT".equalsIgnoreCase(request.paymentMethod)) {
            payment = new DebitPayment();
        }

        double paymentDiscountValue = 0;
        DiscountDetail paymentDiscount = null;
        if (payment != null) {
            paymentDiscount = payment.apply(subtotal);
            paymentDiscountValue = paymentDiscount.getAmount();
        }

        // 5️⃣ Total final
        double total = subtotal + shipping - paymentDiscountValue;

        // 6️⃣ Preparar respuesta
        CheckoutResponse response = new CheckoutResponse();
        response.productsTotal = productsTotal;
        response.productDiscounts = productDiscount.getAmount();
        response.subtotal = subtotal;
        response.shippingCost = shipping;
        response.paymentDiscount = paymentDiscountValue;
        response.total = total;

        List<DiscountDetail> discounts = new ArrayList<>();
        discounts.add(productDiscount);
        if (paymentDiscount != null) discounts.add(paymentDiscount);

        response.discounts = discounts;

        return response;
    }
}