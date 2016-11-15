package ru.ncedu.menu.commands.markets;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Market;


public abstract class MarketSelectionHandlerCommand implements Command {

    protected Market market;

    public MarketSelectionHandlerCommand() {
    }

    public MarketSelectionHandlerCommand(Market market) {
        this.market = market;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

}