package com.wigell.webshop.models;

import com.wigell.webshop.models.clothes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {
    private Order order;
    private Receipt receipt;
    private Customer customer;
    private Pants pants;
    private TShirt tshirt;
    private Skirt skirt;

    @BeforeEach
    void setUp() {
        customer = new Customer(1, "Test Kund", "Testgatan 1", "test@example.com");
        order = new Order(101, customer);

        pants = new Pants(1, "M", "Bomull", "Blå", 499.99, "Slim", "Lång");
        tshirt = new TShirt(2, "L", "Polyester", "Röd", 299.99, "Kort", "V-ringad");
        skirt = new Skirt(3, "S", "Siden", "Svart", 399.99, "Hög", "Prickig");

        order.addClothes(pants);
        order.addClothes(tshirt);
        order.addClothes(skirt);

        receipt = new Receipt(order);

        System.out.println("\n🔄 Setup: Skapat ny kund, order och kvitto.");
        System.out.println("🛍 Kund: " + customer.getName() + " (" + customer.getAddress() + ")");
        System.out.println("📦 Order ID: " + order.getId());
    }

    @Test
    void testReceiptContent() {
        System.out.println("\n✅ TEST: Generera kvitto.");

        String receiptText = receipt.toString();
        assertTrue(receiptText.contains(customer.getName()), "Kundens namn ska finnas i kvittot.");
        assertTrue(receiptText.contains(pants.getName()), "Byxor ska finnas i kvittot.");
        assertTrue(receiptText.contains(tshirt.getName()), "T-shirt ska finnas i kvittot.");
        assertTrue(receiptText.contains(skirt.getName()), "Kjol ska finnas i kvittot.");
        assertTrue(receiptText.contains(String.valueOf(order.getClothesList().size())), "Antal plagg ska finnas i kvittot.");
        assertTrue(receiptText.contains(String.valueOf(getTotalPrice())), "Totalpriset ska finnas i kvittot.");

        System.out.println("🧾 Genererat kvitto:");
        System.out.println(receiptText);
    }


    // Hjälpmetod för att räkna ut totalpriset av ordern
    private double getTotalPrice() {
        double total = 0;
        for (Clothes c : order.getClothesList()) {
            total += c.getPrice();
        }
        return total;
    }

    @Test
    void testReceiptTotalPrice() {
        double expectedTotal = pants.getPrice() + tshirt.getPrice() + skirt.getPrice();
        String receiptText = receipt.toString();

        String formattedTotal = String.format(Locale.US,"%.2f", expectedTotal);


        assertTrue(receiptText.contains(formattedTotal) || receiptText.contains(formattedTotal + " kr"), "Totalpriset på kvittot ska vara korrekt.");}

}