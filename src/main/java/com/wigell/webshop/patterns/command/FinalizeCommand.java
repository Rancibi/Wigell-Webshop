package com.wigell.webshop.patterns.command;

import com.wigell.webshop.models.clothes.*;

public class FinalizeCommand implements Command {
    private Clothes clothes;

    public FinalizeCommand(Clothes clothes) {
        this.clothes = clothes;
    }

    @Override
    public void execute() {
        clothes.finalizeClothes();
    }
}
