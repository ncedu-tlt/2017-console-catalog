package ru.ncedu.menu.commands.prices;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Price;
import ru.ncedu.menu.repositories.PricesRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class SelectPriceCommand implements Command{

    private PriceSelectionHandlerCommand selectionHandlerCommand;

    public SelectPriceCommand(){}

    public SelectPriceCommand(PriceSelectionHandlerCommand selectionHandlerCommand){
        this.selectionHandlerCommand = selectionHandlerCommand;
    }

    @Override
    public Command execute() {
        List<Price> prices = PricesRepository.getInstance().get();

        MenuUtils.printSeparator();

        if (prices.isEmpty()){
            System.out.println("Prise is not found");
            return PriceMenuCommand.getInstance();
        }

        for (Price price: prices){
            System.out.println("Market ID " + String.valueOf(price.getMarketId()) + "\n" + "Product ID " + String.valueOf(price.getProductId()) + "\n" + "Amount " + String.valueOf(price.getAmount()));
        }
        MenuUtils.printOption("0", "Back");
        MenuUtils.printSeparator();
        MenuUtils.printPrompt();
        Scanner scanner = new Scanner(System.in);
        long marketId = scanner.nextLong();
        long productId = scanner.nextLong();

        if (marketId == 0){
            return PriceMenuCommand.getInstance();
        }
        if (productId == 0){
            return PriceMenuCommand.getInstance();
        }
        Price price = PricesRepository.getInstance().get(marketId, productId);
        if (price != null) {
            selectionHandlerCommand.setPriceSelection(price);
            return selectionHandlerCommand;
        }

        MenuUtils.printSeparator();
        System.out.println("No found!");

        return this;
    }
}
