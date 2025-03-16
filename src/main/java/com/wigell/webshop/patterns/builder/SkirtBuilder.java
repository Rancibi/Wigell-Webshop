package com.wigell.webshop.patterns.builder;

import com.wigell.webshop.models.clothes.Skirt;

public class SkirtBuilder implements ClothesBuilder {
    private final Skirt skirt;

    public SkirtBuilder() {
        this.skirt = new Skirt();
        skirt.setPrice(399.99);
    }

    @Override
    public SkirtBuilder setSize(String size) {
        skirt.setSize(size);
        return this;
    }

    @Override
    public SkirtBuilder setMaterial(String material) {
        skirt.setMaterial(material);
        return this;
    }

    @Override
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

    @Override
    public Skirt build() {
        return skirt;
    }
}
