package com.example.checkout.promotion;

import com.example.checkout.model.*;

import java.util.Map;

public class BulkDiscountPromotion implements Promotion {

    /**
     * Aplica un descuento por compra múltiple (bulk discount)
     * Ejemplo: si se compran 2 o más unidades de un producto, se aplica 10% de descuento en ese producto
     *
     * @param request      Carrito de compras
     * @param productCatalog  Mapa de SKU -> Product para obtener precio y nombre
     * @return DiscountDetail con total de descuento aplicado
     */
    public DiscountDetail apply(CartRequest request, Map<String, Product> productCatalog) {
        double discount = 0;

        for (CartItem item : request.items) {
            Product product = productCatalog.get(item.sku);
            if (product != null && item.quantity >= 2) {
                // Aplicar 10% de descuento por producto que tenga 2 o más unidades
                discount += product.getPrice() * item.quantity * 0.10; // <-- Se usa getPrice()
            }
        }

        return new DiscountDetail("PRODUCT", "10% descuento por compra múltiple", discount);
    }

    // Método adicional para compatibilidad con la interfaz original (sin catálogo)
    @Override
    public DiscountDetail apply(CartRequest request) {
        // Si se llama sin catálogo, no se aplica descuento
        return new DiscountDetail("PRODUCT", "No aplica descuento", 0);
    }
}