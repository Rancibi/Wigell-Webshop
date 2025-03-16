package com.wigell.webshop.models.clothes;

public class TShirt extends Clothes {
    private String sleeves;
    private String neck;

    public TShirt() {
        setName("T-shirt");
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
