package com.wigell.webshop.models.clothes;

public abstract class Clothes {
    private String name;
    private String size;
    private String material;
    private String color;
    private String decoration;
    private boolean finalized = false;
    private double price;

    public Clothes() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDecoration() { return decoration; }
    public void setDecoration(String decoration) { this.decoration = decoration; }

    public void finalizeClothes(){ this.finalized = true; }
    public boolean isFinalized() { return finalized; }

    @Override
    public String toString() {
        return name + " [Storlek: " + size + ", Material: " + material + ", Färg: " + color + ", Pris: " + price + " kr";
    }
}
