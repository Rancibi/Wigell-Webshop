package com.wigell.webshop.app;

import com.wigell.webshop.models.*;
import com.wigell.webshop.models.clothes.*;
import com.wigell.webshop.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MainAppTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private MainApp mainApp;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    private void setUserInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MainApp.setScanner(new Scanner(System.in));
    }

    @Test
    void testPromptUser() {
        setUserInput("TestUser\n");
        String name = MainApp.promptUser("Ange ditt namn: ");
        assertEquals("TestUser", name);
    }

    @Test
    void testPromptUser_EmptyInput() {
        setUserInput("\nValidName\n");
        String name = MainApp.promptUser("Ange ditt namn: ");
        assertEquals("ValidName", name);
        assertTrue(outputStreamCaptor.toString().contains("⚠ Du måste ange ett värde."));
    }

    @Test
    void testPromptInt() {
        setUserInput("2\n");
        int choice = MainApp.promptInt("Ditt val (1-3): ", 1, 3);
        assertEquals(2, choice);
    }

    @Test
    void testPromptInt_InvalidInput() {
        setUserInput("abc\n2\n");
        int choice = MainApp.promptInt("Ditt val (1-3): ", 1, 3);
        assertEquals(2, choice);
        assertTrue(outputStreamCaptor.toString().contains("⚠ Ogiltig input. Ange ett heltal."));
    }

    @Test
    void testPromptChoice_InvalidInput() {
        setUserInput("X\nM\n");
        String size = MainApp.promptChoice("Välj storlek", new String[]{"S", "M", "L"});
        assertEquals("M", size);
        assertTrue(outputStreamCaptor.toString().contains("⚠ Ogiltigt val. Välj mellan: S, M, L"));
    }

    @Test
    void testPromptYesNo_InvalidInput() {
        setUserInput("kanske\nja\n");
        boolean result = MainApp.promptYesNo("Vill du beställa fler plagg? (ja/nej): ");
        assertTrue(result);
        assertTrue(outputStreamCaptor.toString().contains("⚠ Svara med 'ja' eller 'nej'."));
    }

    @Test
    void testChooseClothes_InvalidOption() {
        setUserInput("4\n1\nM\nBomull\nBlå\nRegular\nLång\n");
        Clothes chosen = MainApp.chooseClothes();
        assertNotNull(chosen);
        assertTrue(chosen instanceof Pants);
        assertTrue(outputStreamCaptor.toString().contains("⚠ Ange ett nummer mellan 1 och 3."));
    }


    @Test
    void testChooseClothes_Pants() {
        setUserInput("1\nM\nBomull\nBlå\nRegular\nLång\n");
        Clothes chosen = MainApp.chooseClothes();
        assertNotNull(chosen);
        assertTrue(chosen instanceof Pants);
        assertEquals("Blå", chosen.getColor());
    }

    @Test
    void testChooseClothes_TShirt() {
        setUserInput("2\nL\nPolyester\nRöd\nKort\nV-ringad\n");
        Clothes chosen = MainApp.chooseClothes();
        assertNotNull(chosen);
        assertTrue(chosen instanceof TShirt);
        assertEquals("Röd", chosen.getColor());
    }

    @Test
    void testChooseClothes_Skirt() {
        setUserInput("3\nS\nSiden\nSvart\nHög\nRandig\n");
        Clothes chosen = MainApp.chooseClothes();
        assertNotNull(chosen);
        assertTrue(chosen instanceof Skirt);
        assertEquals("Svart", chosen.getColor());
    }

    @Test
    void testOrderPlacement() {
        setUserInput("TestUser\nTestAddress\nTestEmail\nja\n1\nM\nBomull\nBlå\nRegular\nLång\nnej\n\n");
        MainApp.setOrderService(orderService);
        MainApp.runShop();
        verify(orderService, atLeastOnce()).placeOrder(any(Order.class));
        assertTrue(outputStreamCaptor.toString().contains("📜 Ditt kvitto:"));
    }

    @Test
    void testMainMethod() {
        setUserInput("TestUser\nTestAddress\nTestEmail\nja\n1\nM\nBomull\nBlå\nRegular\nLång\nnej\n\n");
        assertDoesNotThrow(() -> MainApp.main(new String[]{}));
        assertTrue(outputStreamCaptor.toString().contains("📢 Välkommen till Wigell Webshop! 📢"));
    }
}
