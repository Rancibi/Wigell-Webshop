package com.wigell.webshop.patterns.command;

import com.wigell.webshop.models.clothes.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DecorateCommandTest {

    @Test
    void testExecute() {
        Pants pants = new Pants(1, "M", "Bomull", "Blå", 499.99, "Slim", "Lång");
        DecorateCommand command = new DecorateCommand(pants, "Extra fickor");

        command.execute();

        // Ingen faktisk förändring sker i objektet, men testar att kommandot körs utan fel
        assertNotNull(command);
    }
}
