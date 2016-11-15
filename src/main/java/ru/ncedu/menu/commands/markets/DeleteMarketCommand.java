package ru.ncedu.menu.commands.markets;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.models.Market;
import ru.ncedu.menu.repositories.MarketRepository;
import ru.ncedu.menu.utils.MenuUtils;


public class DeleteMarketCommand extends MarketSelectionHandlerCommand {

    public DeleteMarketCommand() {
    }

    public DeleteMarketCommand(Market market) {
        super(market);
    }

    @Override
    public Command execute() {

        MarketRepository.getInstance().remove(market);

        MenuUtils.printSeparator();
        System.out.println("Market '" + market.getName() + "' have been deleted");

        return MainMenuCommand.getInstance();
    }
}
