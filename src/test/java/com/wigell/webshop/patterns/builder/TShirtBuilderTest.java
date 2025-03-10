package com.wigell.webshop.patterns.builder;

import com.wigell.webshop.models.clothes.TShirt;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TShirtBuilderTest {

    @Test
    void testBuildTShirt() {
        TShirt tshirt = new TShirtBuilder()
                .setSize("L")
                .setMaterial("Polyester")
                .setColor("Röd")
                .setSleeves("Kort")
                .setNeck("V-ringad")
                .build();

        assertNotNull(tshirt, "TShirt-objektet ska skapas");
        assertEquals("L", tshirt.getSize(), "Storlek ska vara L");
        assertEquals("Polyester", tshirt.getMaterial(), "Material ska vara Polyester");
        assertEquals("Röd", tshirt.getColor(), "Färg ska vara Röd");
        assertEquals("Kort", tshirt.getSleeves(), "Ärmlängd ska vara Kort");
        assertEquals("V-ringad", tshirt.getNeck(), "Halsringning ska vara V-ringad");
    }
}
