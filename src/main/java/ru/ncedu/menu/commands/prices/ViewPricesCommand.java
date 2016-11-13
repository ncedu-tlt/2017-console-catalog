package ru.ncedu.menu.commands.prices;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Price;
import ru.ncedu.menu.repositories.PricesRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class ViewPricesCommand implements Command {

    private static ViewPricesCommand instance;
    private ViewPricesCommand() {}
    public static synchronized ViewPricesCommand getInstance(){
        if (instance == null){
            instance = new ViewPricesCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        List<Price> prices = PricesRepository.getInstance().get();

        MenuUtils.printSeparator();

        if (prices.isEmpty()){
            System.out.println("Price not found");
            return PriceMenuCommand.getInstance();
        }

        for (Price price: prices){
            System.out.println("Market ID = " + String.valueOf(price.getMarketId()) + "\n" + "Product ID = " + String.valueOf(price.getProductId()) + "\n" + "Amount " + String.valueOf(price.getAmount()) + "\n");
        }
        MenuUtils.printSeparator();
        System.out.println("Press Enter to continue");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        return PriceMenuCommand.getInstance();
    }
}
