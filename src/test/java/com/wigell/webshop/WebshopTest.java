package com.wigell.webshop;

import com.wigell.webshop.models.*;
import com.wigell.webshop.models.clothes.*;
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
        Pants pants = new Pants(1, "M", "Bomull", "Blå", 499.99, "Slim", "Lång");
        TShirt tshirt = new TShirt(2, "L", "Polyester", "Röd", 299.99, "Kort", "V-ringad");

        order.addClothes(pants);
        order.addClothes(tshirt);

        // Lägg ordern
        orderService.placeOrder(order);

        // Slutför ordern
        orderService.completeOrder(101);
    }
}
