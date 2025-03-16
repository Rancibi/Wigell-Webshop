package com.wigell.webshop;

import com.wigell.webshop.models.*;
import com.wigell.webshop.models.clothes.*;
import com.wigell.webshop.patterns.builder.*;
import com.wigell.webshop.services.OrderService;

public class WebshopTest {
    public static void main(String[] args) {
        // Skapa CEO (Observer)
        CEO ceo = new CEO("Lars Wigell");

        // Hämta Singleton-instansen av OrderService
        OrderService orderService = OrderService.getInstance();

        // Lägg till VD som observatör
        orderService.addObserver(ceo);

        // Skapa kund
        Customer customer = new Customer(1, "Anna Svensson", "Kungsgatan 10, Stockholm", "anna@example.com");

        // Skapa en order
        Order order = new Order(101, customer);

        // Lägg till kläder
        Pants pants = new PantsBuilder()
                .setSize("M")
                .setMaterial("Bomull")
                .setColor("Blå")
                .setFit("Slim")
                .setLength("Lång")
                .build();

        TShirt tshirt = new TShirtBuilder()
                .setSize("L")
                .setMaterial("Polyester")
                .setColor("Röd")
                .setSleeves("Kort")
                .setNeck("V-ringad")
                .build();

        order.addClothes(pants);
        order.addClothes(tshirt);

        // Lägg ordern
        for (Clothes clothes : order.getClothesList()) {
            orderService.placeOrder(order, clothes);
        }
        // Slutför ordern
        orderService.completeOrder(101);
    }
}
