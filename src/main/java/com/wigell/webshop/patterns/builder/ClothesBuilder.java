package com.wigell.webshop.patterns.builder;

import com.wigell.webshop.models.clothes.Clothes;

public interface ClothesBuilder {
    ClothesBuilder setSize(String size);
    ClothesBuilder setMaterial(String material);
    ClothesBuilder setColor(String color);
    Clothes build();
}
