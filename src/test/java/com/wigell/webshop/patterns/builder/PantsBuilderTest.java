package com.wigell.webshop.patterns.builder;

import com.wigell.webshop.models.clothes.Pants;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PantsBuilderTest {

    @Test
    void testBuildPants() {
        Pants pants = new PantsBuilder()
                .setSize("M")
                .setMaterial("Bomull")
                .setColor("Blå")
                .setFit("Slim")
                .setLength("Lång")
                .build();

        assertNotNull(pants, "Pants-objektet ska skapas");
        assertEquals("M", pants.getSize(), "Storlek ska vara M");
        assertEquals("Bomull", pants.getMaterial(), "Material ska vara Bomull");
        assertEquals("Blå", pants.getColor(), "Färg ska vara Blå");
        assertEquals("Slim", pants.getFit(), "Passform ska vara Slim");
        assertEquals("Lång", pants.getLength(), "Längd ska vara Lång");
    }
}
