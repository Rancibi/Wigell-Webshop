package com.wigell.webshop.services;

import com.wigell.webshop.models.*;
import com.wigell.webshop.models.clothes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class OrderServiceTest {
    private OrderService orderService;
    private Customer customer;
    private Order order;
    private CEO ceoObserver;
    private Pants pants;
    private TShirt tshirt;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        orderService = OrderService.getInstance();
        orderService.reset();  // Rensar orders mellan tester
        customer = new Customer(1, "Test Kund", "Testgatan 1", "test@example.com");
        order = new Order(100, customer);
        ceoObserver = new CEO("Lars Wigell");

        pants = new Pants(1, "M", "Bomull", "Blå", 499.99, "Slim", "Lång");
        tshirt = new TShirt(2, "L", "Polyester", "Röd", 299.99, "Kort", "V-ringad");
    }

    @Test
    void testSingletonInstance() {
        OrderService instance1 = OrderService.getInstance();
        OrderService instance2 = OrderService.getInstance();
        assertSame(instance1, instance2, "OrderService ska vara en Singleton");
    }

    @Test
    void testAddObserver() {
        orderService.addObserver(ceoObserver);
        assertDoesNotThrow(() -> orderService.placeOrder(order),
                "Observern ska kunna ta emot notifieringar vid orderläggning");
    }

    @Test
    void testPlaceOrder() {
        orderService.addObserver(ceoObserver); // Lägg till en observer
        order.addClothes(pants);
        order.addClothes(tshirt);

        orderService.placeOrder(order);
        List<Order> orders = orderService.getOrders();

        assertEquals(1, orders.size(), "Orderlistan ska innehålla 1 order");
        assertEquals(order, orders.get(0), "Ordern ska vara den som lades till");
        assertEquals(2, orders.get(0).getClothesList().size(), "Ordern ska innehålla 2 klädesplagg");
    }

    @Test
    void testCompleteOrder_WithClothes() {
        orderService.addObserver(ceoObserver);
        order.addClothes(pants);
        order.addClothes(tshirt);

        orderService.placeOrder(order);

        assertFalse(order.isCompleted(), "Ordern ska INTE vara färdig direkt efter beställning");

        orderService.completeOrder(100);

        assertTrue(order.isCompleted(), "Ordern ska markeras som färdig");
    }

    @Test
    void testCompleteOrder_NonExisting() {
        orderService.addObserver(ceoObserver);
        orderService.completeOrder(999);  // Testa en order som inte finns

        assertFalse(order.isCompleted(), "Ordern ska inte markeras som färdig om den inte finns");
    }

    @Test
    void testDecorateOrder_Pants() {
        Order order = new Order(101, new Customer(1, "TestUser", "TestAddress", "TestEmail"));
        Pants pants = new Pants(1, "M", "Bomull", "Blå", 499.99, "Regular", "Lång");
        order.addClothes(pants);

        orderService.decorateOrder(order);

        assertTrue(outputStreamCaptor.toString().contains("Lägger till fickor och förstärker sömmar"));
    }

    @Test
    void testDecorateOrder_TShirt() {
        Order order = new Order(101, new Customer(1, "TestUser", "TestAddress", "TestEmail"));
        TShirt tShirt = new TShirt(2, "L", "Polyester", "Röd", 299.99, "Kort", "V-ringad");
        order.addClothes(tShirt);

        orderService.decorateOrder(order);

        assertTrue(outputStreamCaptor.toString().contains("Lägger tryck och syr extra stretch"));
    }

    @Test
    void testDecorateOrder_Skirt() {
        Order order = new Order(101, new Customer(1, "TestUser", "TestAddress", "TestEmail"));
        Skirt skirt = new Skirt(3, "S", "Siden", "Svart", 399.99, "Hög", "Randig");
        order.addClothes(skirt);

        orderService.decorateOrder(order);

        assertTrue(outputStreamCaptor.toString().contains("Lägger spetskant och formar tyget"));
    }
}
