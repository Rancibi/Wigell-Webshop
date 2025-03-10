package com.wigell.webshop.patterns.command;

import com.wigell.webshop.models.clothes.*;
import com.wigell.webshop.models.CEO;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class FinalizeCommandTest {

    @Test
    void testExecute() {

        CEO ceo = new CEO("Lars Wigell");
        Pants pants = new Pants(1, "M", "Bomull", "Blå", 499.99, "Slim", "Lång");
        FinalizeCommand command = new FinalizeCommand(pants, ceo);

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        command.execute();

        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("📢 VD Lars Wigell har fått meddelande: Plagget 'Byxor' är klart för leverans!"));

        assertNotNull(command);
    }
}
