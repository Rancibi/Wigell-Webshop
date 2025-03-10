package com.wigell.webshop.patterns.command;

import com.wigell.webshop.models.clothes.*;
import com.wigell.webshop.patterns.observer.OrderObserver;

public class FinalizeCommand implements Command {
    private Clothes clothes;
    private OrderObserver observer;

    public FinalizeCommand(Clothes clothes, OrderObserver observer) {
        this.clothes = clothes;
        this.observer = observer;
    }

    @Override
    public void execute() {
        observer.update("Plagget '" + clothes.getName() + "' är klart för leverans!");
    }
}
