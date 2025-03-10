package com.wigell.webshop.models.clothes;

public class TShirt extends Clothes {
    private String sleeves;
    private String neck;

    public TShirt() {
        setName("T-shirt");
    }

    public TShirt(int id, String size, String material, String color, double price, String sleeves, String neck) {
        super(id, "T-shirt", size, material, color, price);
        this.sleeves = sleeves;
        this.neck = neck;
    }

    public String getSleeves() { return sleeves; }
    public void setSleeves(String sleeves) { this.sleeves = sleeves; }

    public String getNeck() { return neck; }
    public void setNeck(String neck) { this.neck = neck; }

    @Override
    public String toString() {
        return super.toString() + ", Ärmlängd: " + sleeves + ", Halsringning: " + neck;
    }
}
