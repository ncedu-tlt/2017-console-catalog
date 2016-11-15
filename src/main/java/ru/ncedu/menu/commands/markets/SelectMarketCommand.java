package ru.ncedu.menu.commands.markets;

import java.util.List;
import java.util.Scanner;
import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Market;
import ru.ncedu.menu.repositories.MarketRepository;
import ru.ncedu.menu.utils.MenuUtils;

public class SelectMarketCommand implements Command{

    MarketSelectionHandlerCommand selectionHandlerCommand;

    public SelectMarketCommand(){}

    public SelectMarketCommand(MarketSelectionHandlerCommand marketselectionHandlerCommand){
        this.selectionHandlerCommand = marketselectionHandlerCommand;
    }

    public MarketSelectionHandlerCommand getMarketSelectionHandler() {
        return selectionHandlerCommand;
    }

    public void setMarketSelectionHandler(MarketSelectionHandlerCommand selectionHandler) {
        this.selectionHandlerCommand = selectionHandler;
    }
    @Override
    public Command execute() {
        List<Market> listMarkets = MarketRepository.getInstance().get();

        MenuUtils.printSeparator();

        if (listMarkets.isEmpty()) {
            System.out.println("No markets have been found");
            return MarketMenuCommand.getInstance();
        }

        for (Market market : listMarkets) {
            MenuUtils.printOption(String.valueOf(market.getId()), market.getName());
        }

        MenuUtils.printOption("0", "Back");
        MenuUtils.printSeparator();
        MenuUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        String marketId = scanner.next();

        if (marketId.equals("0")) {
            return MarketMenuCommand.getInstance();
        }

        try {
            long id = Long.parseLong(marketId);
            Market market = MarketRepository.getInstance().get(id);

            if (market != null) {
                selectionHandlerCommand.setMarket(market);
                return selectionHandlerCommand;
            }

            MenuUtils.printSeparator();
            System.out.println("No market with such ID have been found!");

        } catch (NumberFormatException e) {
            MenuUtils.printSeparator();
            System.out.println("Market ID should be a number!");
        }

        return this;
    }
}
