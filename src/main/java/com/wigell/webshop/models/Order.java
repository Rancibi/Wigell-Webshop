package com.wigell.webshop.models;

import com.wigell.webshop.models.clothes.*;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private Customer customer;
    private List<Clothes> clothesList;
    private boolean isCompleted;

    public Order() {
        this.clothesList = new ArrayList<>();
    }

    public Order(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.clothesList = new ArrayList<>();
        this.isCompleted = false;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public List<Clothes> getClothesList() { return clothesList; }
    public void addClothes(Clothes clothes) { this.clothesList.add(clothes); }

    public boolean isCompleted() { return isCompleted; }
    public void completeOrder() { this.isCompleted = true; }

    @Override
    public String toString() {
        return "Order #" + id + " för " + customer.getName() + "\nBeställda plagg: " + clothesList;
    }
}
