package ru.ncedu.menu.commands.prices;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Price;
import ru.ncedu.menu.models.Product;
import ru.ncedu.menu.repositories.PricesRepository;
import ru.ncedu.menu.repositories.ProductsRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class SelectPriceCommand implements Command {

    private PriceSelectionHandlerCommand selectionHandlerCommand;

    public SelectPriceCommand() {
    }

    public SelectPriceCommand(PriceSelectionHandlerCommand selectionHandlerCommand) {
        this.selectionHandlerCommand = selectionHandlerCommand;
    }

    @Override
    public Command execute() {
        List<Price> prices = PricesRepository.getInstance().get();
        List<Product> products = ProductsRepository.getInstance().get();

        MenuUtils.printSeparator();

        if (prices.isEmpty()) {
            System.out.println("Priсe is not found");
            return PriceMenuCommand.getInstance();
        }

        for (Price price : prices) {
            for (Product product : products)
                if (price.getProductId() == product.getId())
                    System.out.println("Market ID " + String.valueOf(price.getMarketId()) + "\n" + "Product ID - " + String.valueOf(price.getProductId()) + " Name: " + product.getName() + "\n" + "Amount " + String.valueOf(price.getAmount() + "\n"));
        }
        MenuUtils.printOption("0", "Back");
        MenuUtils.printSeparator();
        MenuUtils.printPrompt();
        Scanner scanner = new Scanner(System.in);
        String priceNum = scanner.nextLine();
        if (priceNum.trim().equals("0")) {
            return PriceMenuCommand.getInstance();
        }
        System.out.println("Enter market ID");
        long marketId = MenuUtils.getId();
        System.out.println("Enter product ID");
        long productId = MenuUtils.getId();
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
