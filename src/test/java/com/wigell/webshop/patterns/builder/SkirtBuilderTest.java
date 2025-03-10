package com.wigell.webshop.patterns.builder;

import com.wigell.webshop.models.clothes.Skirt;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SkirtBuilderTest {

    @Test
    void testBuildSkirt() {
        Skirt skirt = new SkirtBuilder()
                .setSize("S")
                .setMaterial("Siden")
                .setColor("Svart")
                .setWaistline("Hög")
                .setPattern("Prickig")
                .build();

        assertNotNull(skirt, "Skirt-objektet ska skapas");
        assertEquals("S", skirt.getSize(), "Storlek ska vara S");
        assertEquals("Siden", skirt.getMaterial(), "Material ska vara Siden");
        assertEquals("Svart", skirt.getColor(), "Färg ska vara Svart");
        assertEquals("Hög", skirt.getWaistline(), "Midja ska vara Hög");
        assertEquals("Prickig", skirt.getPattern(), "Mönster ska vara Prickig");
    }
}
