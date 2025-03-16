package com.wigell.webshop.patterns.command;

import com.wigell.webshop.models.clothes.*;
import com.wigell.webshop.patterns.builder.PantsBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DecorateCommandTest {

    @Test
    void testExecute() {
        Pants pants = new PantsBuilder()
                .setSize("M")
                .setMaterial("Bomull")
                .setColor("Blå")
                .setFit("Slim")
                .setLength("Lång")
                .build();
        DecorateCommand command = new DecorateCommand(pants, "Extra fickor");

        command.execute();

        // Ingen faktisk förändring sker i objektet, men testar att kommandot körs utan fel
        assertNotNull(command);
    }
}
