package com.wigell.webshop.services;

import com.wigell.webshop.models.*;
import com.wigell.webshop.models.clothes.*;
import com.wigell.webshop.patterns.builder.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class OrderServiceTest {
    private OrderService orderService;
    private Customer customer;
    private Order order;
    private CEO ceoObserver;
    private Pants pants;
    private TShirt tshirt;
    private Skirt skirt;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        orderService = OrderService.getInstance();
        orderService.reset();  // Rensar orders mellan tester
        customer = new Customer(1, "Test Kund", "Testgatan 1", "test@example.com");
        order = new Order(100, customer);
        ceoObserver = new CEO("Lars Wigell");
        System.setOut(new PrintStream(outputStreamCaptor));

        pants = new PantsBuilder()
                .setSize("M")
                .setMaterial("Bomull")
                .setColor("Blå")
                .setFit("Slim")
                .setLength("Lång")
                .build();

        tshirt = new TShirtBuilder()
                .setSize("L")
                .setMaterial("Polyester")
                .setColor("Röd")
                .setSleeves("Kort")
                .setNeck("V-ringad")
                .build();

        skirt = new SkirtBuilder()
                .setSize("S")
                .setMaterial("Siden")
                .setColor("Svart")
                .setWaistline("Hög")
                .setPattern("Randig")
                .build();
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

        Clothes testClothes = new Pants();

        assertDoesNotThrow(() -> orderService.placeOrder(order, testClothes),
                "Observern ska kunna ta emot notifieringar vid orderläggning");
    }

    @Test
    void testPlaceOrder() {
        orderService.addObserver(ceoObserver); // Lägg till en observer
        order.addClothes(pants);
        order.addClothes(tshirt);

        for (Clothes clothes : order.getClothesList()) {
            orderService.placeOrder(order, clothes);
        }

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

        for (Clothes clothes : order.getClothesList()) {
            orderService.placeOrder(order, clothes);
        }

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
        order.addClothes(pants);

        orderService.decorateOrder(order);

        assertTrue(outputStreamCaptor.toString().contains("Lägger till fickor och förstärker sömmar"));
    }

    @Test
    void testDecorateOrder_TShirt() {
        Order order = new Order(101, new Customer(1, "TestUser", "TestAddress", "TestEmail"));
        order.addClothes(tshirt);

        orderService.decorateOrder(order);

        assertTrue(outputStreamCaptor.toString().contains("Lägger tryck och syr extra stretch"));
    }

    @Test
    void testDecorateOrder_Skirt() {
        Order order = new Order(101, new Customer(1, "TestUser", "TestAddress", "TestEmail"));
        order.addClothes(skirt);

        orderService.decorateOrder(order);

        assertTrue(outputStreamCaptor.toString().contains("Lägger spetskant och formar tyget"));
    }
}
