package com.wigell.webshop.models.clothes;

public class Pants extends Clothes {
    private String fit;
    private String length;

    public Pants() {
        setName("Byxor");
    }

    public Pants(int id, String size, String material, String color, double price, String fit, String length) {
        super(id, "Byxor", size, material, color, price);
        this.fit = fit;
        this.length = length;
    }

    public String getFit() { return fit; }
    public void setFit(String fit) { this.fit = fit; }

    public String getLength() { return length; }
    public void setLength(String length) { this.length = length; }

    @Override
    public String toString() {
        return super.toString() + ", Passform: " + fit + ", Längd: " + length;
    }
}
