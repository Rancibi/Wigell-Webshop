package com.wigell.webshop.app;

import com.wigell.webshop.models.*;
import com.wigell.webshop.models.clothes.*;
import com.wigell.webshop.patterns.builder.PantsBuilder;
import com.wigell.webshop.patterns.builder.SkirtBuilder;
import com.wigell.webshop.patterns.builder.TShirtBuilder;
import com.wigell.webshop.services.OrderService;

import java.util.Scanner;

public class MainApp {
    private static Scanner scanner;

    public static void setScanner(Scanner newScanner) {
        scanner = newScanner;
    }

    private static OrderService orderService;

    public static void setOrderService(OrderService service) {
        orderService = service;
    }

    public static void main(String[] args) {
        orderService = OrderService.getInstance();

        CEO ceo = new CEO("Lars Wigell");
        orderService.addObserver(ceo);

        System.out.println("📢 Välkommen till Wigell Webshop! 📢");
        scanner = new Scanner(System.in);

        try {
            runShop();
        } catch (Exception e) {
            System.out.println("⚠ Ett oväntat fel inträffade: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

     static void runShop() {
        String name = promptUser("Ange ditt namn: ");
        String address = promptUser("Ange din adress: ");
        String email = promptUser("Ange din e-post: ");


        Customer customer = new Customer(1, name, address, email);
        Order order = new Order(101, customer);

        boolean addingMore = true;

        while (addingMore) {
            Clothes clothes = chooseClothes();
            if (clothes != null) {
                order.addClothes(clothes);
                System.out.println("✅ " + clothes.getName() + " har lagts till i din beställning.");

                orderService.placeOrder(order, clothes);
            }
            addingMore = promptYesNo("Vill du beställa fler plagg? (ja/nej): ");
        }

        System.out.println("\n🧵 Plaggen håller på att dekoreras...");
        orderService.decorateOrder(order);

        System.out.print("\nTryck på [Enter] för att se ditt kvitto... ");
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        orderService.completeOrder(order.getId());
        Receipt receipt = new Receipt(order);
        System.out.println("\n📜 Ditt kvitto:");
        System.out.println(receipt);
    }

    public static Clothes chooseClothes() {
        System.out.println("\n📌 Välj typ av plagg:");
        System.out.println("1. Byxor");
        System.out.println("2. T-shirt");
        System.out.println("3. Kjol");

        int choice = promptInt("Ditt val (1-3): ", 1, 3);

        String size = promptChoice("Välj storlek", new String[]{"S", "M", "L"});
        String material = promptChoice("Välj material", new String[]{"Bomull", "Polyester", "Siden"});
        String color = promptChoice("Välj färg", new String[]{"Röd", "Blå", "Svart"});

        switch (choice) {
            case 1:
                String fit = promptChoice("Välj passform", new String[]{"Slim", "Regular"});
                String length = promptChoice("Välj längd", new String[]{"Kort", "Lång"});
                return new PantsBuilder()
                        .setSize(size)
                        .setMaterial(material)
                        .setColor(color)
                        .setFit(fit)
                        .setLength(length)
                        .build();

            case 2:
                String sleeves = promptChoice("Välj ärmlängd", new String[]{"Kort", "Lång"});
                String neck = promptChoice("Välj halsringning", new String[]{"Rund", "V-ringad"});
                return new TShirtBuilder()
                        .setSize(size)
                        .setMaterial(material)
                        .setColor(color)
                        .setSleeves(sleeves)
                        .setNeck(neck)
                        .build();

            case 3:
                String waistline = promptChoice("Välj midja", new String[]{"Hög", "Låg"});
                String pattern = promptChoice("Välj mönster", new String[]{"Prickig", "Randig"});
                return new SkirtBuilder()
                        .setSize(size)
                        .setMaterial(material)
                        .setColor(color)
                        .setWaistline(waistline)
                        .setPattern(pattern)
                        .build();

            default:
                System.out.println("⚠ Ogiltigt val!");
                return null;
        }
    }

    // Läser in en sträng från användaren och ser till att den inte är tom.
    public static String promptUser(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("⚠ Du måste ange ett värde.");
        }
    }

    // Läser in ett heltal från användaren inom ett specificerat intervall.
    public static int promptInt(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("⚠ Ange ett nummer mellan " + min + " och " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("⚠ Ogiltig input. Ange ett heltal.");
            }
        }
    }

    // Låter användaren välja mellan fördefinierade alternativ.
    public static String promptChoice(String prompt, String[] choices) {
        while (true) {
            System.out.print(prompt + " (" + String.join("/", choices) + "): ");
            String input = scanner.nextLine().trim();
            for (String choice : choices) {
                if (input.equalsIgnoreCase(choice)) {
                    return choice;
                }
            }
            System.out.println("⚠ Ogiltigt val. Välj mellan: " + String.join(", ", choices));
        }
    }

    // Låter användaren svara ja eller nej.
    public static boolean promptYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("ja")) return true;
            if (response.equals("nej")) return false;
            System.out.println("⚠ Svara med 'ja' eller 'nej'.");
        }
    }
}
