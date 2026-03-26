1. Checkout Retail con Promociones y Medios de Pago

Autor: Simon Maximo Mejias Zapata
Correo: simonmejzap@gmail.com
Teléfono: 569 6599 4020

Proyecto de ejemplo de un checkout de retail que incluye:

Promociones por compra múltiple (bulk discount).
Descuentos según el medio de pago (por ejemplo, débito).
Cálculo de subtotal, envío y total a pagar.

Este proyecto está desarrollado en Java con Spring Boot y puede ser ejecutado localmente con Maven.

2. Contenido del repositorio
src/main/java/com/example/checkout – código fuente principal.
src/main/java/com/example/checkout/model – modelos de datos (Producto, Carrito, Detalle de Descuento).
src/main/java/com/example/checkout/promotion – lógica de promociones.
src/main/java/com/example/checkout/payment – estrategias de descuento por medio de pago.
CheckoutApplication.java – clase principal para probar el flujo de checkout.
src/main/resources/cart-example.json – ejemplo de carrito de compras usado para pruebas.
3. Requisitos
Java 17 o superior
Maven 3.8+
4. Instalación y ejecución
4.1 Clonar el repositorio
git clone https://github.com/simonmaximomejias/Checkout-Retail-con-Promociones-y-Medios-de-Pago.git
cd Checkout-Retail-con-Promociones-y-Medios-de-Pago
4.2 Compilar el proyecto
mvn clean compile
4.3 Ejecutar la aplicación de prueba (CheckoutApplication.java)
mvn exec:java -Dexec.mainClass="com.example.checkout.CheckoutApplication"

Esto mostrará en consola un cálculo de checkout con descuentos por productos y por medio de pago.

4.4 Ejecutar como servicio Spring Boot (opcional)
mvn spring-boot:run

El endpoint disponible será:

POST http://localhost:8080/checkout
5. Ejemplo de request y respuesta
5.1 Formato original (solo referencia)
{
  "items": [
    { "productId": "1", "name": "Laptop", "price": 100000, "quantity": 1 },
    { "productId": "2", "name": "Mouse", "price": 50000, "quantity": 2 }
  ],
  "paymentMethod": "DEBIT"
}

Nota: Este formato es el ejemplo original. Actualmente, el servicio funciona con el archivo cart-example.json usando sku y quantity.

5.2 Ejemplo real (funciona con la aplicación)

Archivo src/main/resources/cart-example.json:

{
  "cartId":"cart-1001",
  "items":[
    {"sku":"p-001","quantity":1},
    {"sku":"p-010","quantity":2},
    {"sku":"p-003","quantity":1}
  ],
  "shippingAddress":{"street":"Av. Falsa 123","city":"Ciudad","zoneId":"zone-1"},
  "paymentMethod":"DEBIT"
}
Salida esperada en consola
Subtotal productos: 200000.0
Descuento productos: 10000.0
Subtotal después de descuentos: 190000.0
Costo de envío: 0.0
Descuento pago: 19000.0
Total a pagar: 171000.0
6. Diseño y decisiones de implementación
6.1 Modelos separados
Product → representa un producto con ID, nombre y precio.
CartItem → representa un item en el carrito.
DiscountDetail → representa cualquier descuento aplicado.
6.2 Promociones como estrategia
Promotion es una interfaz que permite agregar fácilmente nuevas promociones.
BulkDiscountPromotion aplica 10% de descuento si se compran 2 o más unidades de un producto.
6.3 Descuentos por medio de pago
PaymentStrategy permite agregar nuevos tipos de pagos que tengan descuentos.
DebitPayment aplica descuento por pagar con débito.
6.4 CheckoutService
Centraliza la lógica de cálculo de total, aplicación de promociones y descuentos de pago.
Calcula subtotal, envío y total final de manera modular.
6.5 Extensibilidad
Se puede agregar fácilmente nuevas promociones o estrategias de pago sin modificar la lógica existente.
7. Posibles mejoras
Integración con base de datos para manejar productos dinámicamente.
API REST completa para exponer el servicio de checkout.
Soporte para más métodos de pago y tipos de promociones.
Tests unitarios automáticos para garantizar la correcta aplicación de descuentos.
8. Licencia

Este proyecto se entrega bajo MIT License, permitiendo que cualquiera con el enlace pueda clonar y ejecutar el proyecto.
