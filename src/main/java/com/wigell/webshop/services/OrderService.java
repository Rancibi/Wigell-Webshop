package com.wigell.webshop.services;

import com.wigell.webshop.models.Order;
import com.wigell.webshop.models.CEO;
import com.wigell.webshop.models.clothes.*;
import com.wigell.webshop.patterns.command.*;
import com.wigell.webshop.patterns.observer.OrderObserver;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static OrderService instance;
    private List<Order> orders;
    private List<OrderObserver> observers;

    private OrderService() {
        orders = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String message) {
        for (OrderObserver observer : observers) {
            observer.update(message);
        }
    }

    public void placeOrder(Order order, Clothes clothes) {
        orders.add(order);
        notifyObservers("Plagg tillverkas - " + clothes.getName() + " åt kund " + order.getCustomer().getName() + "\n");
    }

    public void decorateOrder(Order order) {
        CommandInvoker invoker = new CommandInvoker();

        for (Clothes c : order.getClothesList()) {
            String decoration = "";
            if (c instanceof Pants) {
                decoration = "Lägger till fickor och förstärker sömmar";
            } else if (c instanceof TShirt) {
                decoration = "Lägger tryck och syr extra stretch";
            } else if (c instanceof Skirt) {
                decoration = "Lägger spetskant och formar tyget";
            }
            invoker.addCommand(new DecorateCommand(c, decoration));
        }

        invoker.executeCommands();

        for (Clothes c : order.getClothesList()) {
            System.out.println("Dekorerar " + c.getName() + " med " + c.getDecoration());
        }
        System.out.println("");

        for (Clothes c : order.getClothesList()) {
            notifyObservers("Plagget '" + c.getName() + "' är klart för leverans!");
        }
    }

    public void completeOrder(int orderId) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                order.completeOrder();
                CommandInvoker invoker = new CommandInvoker();

                for (Clothes c : order.getClothesList()) {
                    invoker.addCommand(new FinalizeCommand(c));
                }

                invoker.executeCommands();

                notifyObservers("Order #" + orderId + " åt kund" + order.getCustomer().getName() + " är redo för leverans!");
                return;
            }
        }
        System.out.println("⚠ Order med ID " + orderId + " hittades inte.");
    }


    public List<Order> getOrders() {
        return orders;
    }

    protected void reset() {
        orders.clear();
    }
}
