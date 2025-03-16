package com.wigell.webshop.patterns.command;

import com.wigell.webshop.models.clothes.*;
import com.wigell.webshop.models.CEO;
import com.wigell.webshop.patterns.builder.PantsBuilder;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class FinalizeCommandTest {

    @Test
    void testExecute() {

        CEO ceo = new CEO("Lars Wigell");
        Pants pants = new PantsBuilder()
                .setSize("M")
                .setMaterial("Bomull")
                .setColor("Blå")
                .setFit("Slim")
                .setLength("Lång")
                .build();
        FinalizeCommand command = new FinalizeCommand(pants);

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        command.execute();

        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("📢 VD Lars Wigell har fått meddelande: Plagget 'Byxor' är klart för leverans!"));

        assertNotNull(command);
    }
}
