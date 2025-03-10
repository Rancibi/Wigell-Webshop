package com.wigell.webshop.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CEOTest {
    private CEO ceo;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        ceo = new CEO("Lars Wigell");
        System.setOut(new PrintStream(outContent)); // Fångar konsolutskrift
    }

    @Test
    void testConstructor() {
        assertNotNull(ceo, "CEO-objektet ska skapas.");
        assertEquals("Lars Wigell", ceo.getName(), "VD-namnet ska vara korrekt.");
    }

    @Test
    void testGetName() {
        assertEquals("Lars Wigell", ceo.getName(), "getName() ska returnera rätt namn.");
    }

    @Test
    void testUpdate() {
        ceo.update("Testmeddelande");

        String expectedOutput = "📢 VD Lars Wigell har fått meddelande: Testmeddelande";
        assertTrue(outContent.toString().trim().contains(expectedOutput),
                "update() ska skriva ut rätt meddelande.");
    }
}
