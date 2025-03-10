package com.wigell.webshop.models.clothes;

public class Skirt extends Clothes {
    private String waistline;
    private String pattern;

    public Skirt() {
        setName("Kjol");
    }

    public Skirt(int id, String size, String material, String color, double price, String waistline, String pattern) {
        super(id, "Kjol", size, material, color, price);
        this.waistline = waistline;
        this.pattern = pattern;
    }

    public String getWaistline() { return waistline; }
    public void setWaistline(String waistline) { this.waistline = waistline; }

    public String getPattern() { return pattern; }
    public void setPattern(String pattern) { this.pattern = pattern; }

    @Override
    public String toString() {
        return super.toString() + ", Midja: " + waistline + ", Mönster: " + pattern;
    }
}
