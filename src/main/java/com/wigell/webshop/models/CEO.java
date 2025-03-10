package com.wigell.webshop.models;

import com.wigell.webshop.patterns.observer.OrderObserver;

public class CEO implements OrderObserver {
    private String name;


    public CEO(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    public void update(String message) {
        System.out.println("📢 VD " + name + " har fått meddelande: " + message);
    }
}
