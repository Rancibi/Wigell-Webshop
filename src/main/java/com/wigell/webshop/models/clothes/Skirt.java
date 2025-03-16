package com.wigell.webshop.models.clothes;

public class Skirt extends Clothes {
    private String waistline;
    private String pattern;

    public Skirt() {
        setName("Kjol");
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
