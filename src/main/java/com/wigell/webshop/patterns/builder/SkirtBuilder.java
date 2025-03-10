package com.wigell.webshop.patterns.builder;

import com.wigell.webshop.models.clothes.Skirt;

public class SkirtBuilder {
    private final Skirt skirt;

    public SkirtBuilder() {
        this.skirt = new Skirt();
    }

    public SkirtBuilder setSize(String size) {
        skirt.setSize(size);
        return this;
    }

    public SkirtBuilder setMaterial(String material) {
        skirt.setMaterial(material);
        return this;
    }

    public SkirtBuilder setColor(String color) {
        skirt.setColor(color);
        return this;
    }

    public SkirtBuilder setWaistline(String waistline) {
        skirt.setWaistline(waistline);
        return this;
    }

    public SkirtBuilder setPattern(String pattern) {
        skirt.setPattern(pattern);
        return this;
    }

    public Skirt build() {
        return skirt;
    }
}
