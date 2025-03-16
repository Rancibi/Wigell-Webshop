package com.wigell.webshop.patterns.builder;

import com.wigell.webshop.models.clothes.Pants;

public class PantsBuilder implements ClothesBuilder {
    private final Pants pants;

    public PantsBuilder() {
        this.pants = new Pants();
        pants.setPrice(499.99);
    }

    @Override
    public PantsBuilder setSize(String size) {
        pants.setSize(size);
        return this;
    }

    @Override
    public PantsBuilder setMaterial(String material) {
        pants.setMaterial(material);
        return this;
    }

    @Override
    public PantsBuilder setColor(String color) {
        pants.setColor(color);
        return this;
    }

    public PantsBuilder setFit(String fit) {
        pants.setFit(fit);
        return this;
    }

    public PantsBuilder setLength(String length) {
        pants.setLength(length);
        return this;
    }

    @Override
    public Pants build() {
        return pants;
    }
}
