package com.wigell.webshop.models;

import com.wigell.webshop.models.clothes.*;
import com.wigell.webshop.patterns.builder.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    private Order order;
    private Customer customer;
    private Pants pants;
    private TShirt tshirt;
    private Skirt skirt;

    @BeforeEach
    void setUp() {
        customer = new Customer(1, "Test Kund", "Testgatan 1", "test@example.com");
        order = new Order(101, customer);

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
                .setPattern("Prickig")
                .build();

        System.out.println("\n🔄 Setup: Skapat ny kund och order.");
        System.out.println("🛍 Kund: " + customer.getName() + " (" + customer.getAddress() + ")");
        System.out.println("📦 Order ID: " + order.getId());
    }

    @Test
    void testDefaultConstructor() {
        System.out.println("\n✅ TEST: Standardkonstruktorn.");
        Order emptyOrder = new Order();
        assertNotNull(emptyOrder.getClothesList(), "Klädeslistan ska inte vara null.");
        assertEquals(0, emptyOrder.getClothesList().size(), "En ny order ska ha en tom klädeslista.");
    }

    @Test
    void testSetAndGetId() {
        System.out.println("\n✅ TEST: Sätt och hämta ID.");
        order.setId(202);
        assertEquals(202, order.getId(), "Order ID ska vara 202 efter setId().");
    }

    @Test
    void testSetAndGetCustomer() {
        System.out.println("\n✅ TEST: Sätt och hämta kund.");
        Customer newCustomer = new Customer(2, "Ny Kund", "Nygatan 5", "ny@example.com");
        order.setCustomer(newCustomer);
        assertEquals(newCustomer, order.getCustomer(), "Kunden ska uppdateras korrekt.");
    }

    @Test
    void testAddClothes() {
        System.out.println("\n✅ TEST: Lägg till klädesplagg i ordern.");
        order.addClothes(pants);
        order.addClothes(tshirt);
        order.addClothes(skirt);

        List<Clothes> clothesList = order.getClothesList();
        assertEquals(3, clothesList.size(), "Det ska finnas tre plagg i ordern.");

        System.out.println("🛒 Order innehåller nu: ");
        for (Clothes c : clothesList) {
            System.out.println(formatClothesDetails(c));
        }
    }

    @Test
    void testCompleteOrder() {
        System.out.println("\n✅ TEST: Markera ordern som färdig.");
        assertFalse(order.isCompleted(), "Ordern ska INTE vara färdig från början.");
        order.completeOrder();
        assertTrue(order.isCompleted(), "Ordern ska markeras som färdig.");
        System.out.println("✅ Order #" + order.getId() + " är nu färdigställd.");
    }

    @Test
    void testToString() {
        System.out.println("\n✅ TEST: toString() för order.");
        order.addClothes(pants);
        order.addClothes(tshirt);

        String orderString = order.toString();
        assertTrue(orderString.contains("Order #101"), "Ordersträngen ska innehålla ordernumret.");
        assertTrue(orderString.contains("Test Kund"), "Ordersträngen ska innehålla kundens namn.");
        assertTrue(orderString.contains("Beställda plagg"), "Ordersträngen ska innehålla 'Beställda plagg'.");
    }


     // Hjälpmetod för att formatera utskrift av klädesplagg.

    private String formatClothesDetails(Clothes clothes) {
        StringBuilder details = new StringBuilder();
        details.append("   → ").append(clothes.getName())
                .append(" [").append(clothes.getSize())
                .append(", ").append(clothes.getMaterial())
                .append(", ").append(clothes.getColor());

        if (clothes instanceof Pants) {
            Pants p = (Pants) clothes;
            details.append(", ").append(p.getFit())
                    .append(", ").append(p.getLength());
        } else if (clothes instanceof TShirt) {
            TShirt t = (TShirt) clothes;
            details.append(", ").append(t.getSleeves())
                    .append(", ").append(t.getNeck());
        } else if (clothes instanceof Skirt) {
            Skirt s = (Skirt) clothes;
            details.append(", ").append(s.getWaistline())
                    .append(", ").append(s.getPattern());
        }

        details.append("]");
        return details.toString();
    }
}
