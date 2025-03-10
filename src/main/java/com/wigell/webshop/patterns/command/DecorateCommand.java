package com.wigell.webshop.patterns.command;

import com.wigell.webshop.models.clothes.Clothes;

public class DecorateCommand implements Command {
    private Clothes clothes;
    private String decoration;

    public DecorateCommand(Clothes clothes, String decoration) {
        this.clothes = clothes;
        this.decoration = decoration;
    }

    @Override
    public void execute() {
        System.out.println("Dekorerar " + clothes.getName() + " med: " + decoration);
    }
}
