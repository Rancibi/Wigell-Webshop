package com.wigell.webshop.models.clothes;

public abstract class Clothes {
    private int id;
    private String name;
    private String size;
    private String material;
    private String color;
    private double price;

    public Clothes() {}

    public Clothes(int id, String name, String size, String material, String color, double price) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.material = material;
        this.color = color;
        this.price = price;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " [Storlek: " + size + ", Material: " + material + ", Färg: " + color + ", Pris: " + price + " kr";
    }
}
