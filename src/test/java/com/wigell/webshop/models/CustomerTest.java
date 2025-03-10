package com.wigell.webshop.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer(1, "Anna Svensson", "Kungsgatan 10", "anna@example.com");
    }

    @Test
    void testConstructor() {
        assertNotNull(customer, "Customer-objektet ska skapas.");
        assertEquals(1, customer.getId(), "ID ska vara 1.");
        assertEquals("Anna Svensson", customer.getName(), "Namn ska vara Anna Svensson.");
        assertEquals("Kungsgatan 10", customer.getAddress(), "Adress ska vara Kungsgatan 10.");
        assertEquals("anna@example.com", customer.getMail(), "Mail ska vara anna@example.com.");
    }

    @Test
    void testSetAndGetId() {
        customer.setId(2);
        assertEquals(2, customer.getId(), "ID ska vara 2 efter setId().");
    }

    @Test
    void testSetAndGetName() {
        customer.setName("Erik Karlsson");
        assertEquals("Erik Karlsson", customer.getName(), "Namnet ska vara Erik Karlsson efter setName().");
    }

    @Test
    void testSetAndGetAddress() {
        customer.setAddress("Storgatan 5");
        assertEquals("Storgatan 5", customer.getAddress(), "Adressen ska vara Storgatan 5 efter setAddress().");
    }

    @Test
    void testSetAndGetMail() {
        customer.setMail("erik@example.com");
        assertEquals("erik@example.com", customer.getMail(), "Mail ska vara erik@example.com efter setMail().");
    }
}
