package ru.ncedu.menu.commands.prices;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Price;

public abstract class PriceSelectionHandlerCommand implements Command {

    protected Price price;

    public PriceSelectionHandlerCommand() {
    }

    public PriceSelectionHandlerCommand(Price price) {
        this.price = price;
    }

    public void setPriceSelection(Price price) {
        this.price = price;
    }
}
