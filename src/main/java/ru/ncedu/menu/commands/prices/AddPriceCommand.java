package ru.ncedu.menu.commands.prices;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Price;
import ru.ncedu.menu.repositories.PricesRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AddPriceCommand implements Command{

    private static AddPriceCommand instance;

    private List<Price> prices = PricesRepository.getInstance().get();

    private AddPriceCommand() {}

    public static synchronized AddPriceCommand getInstance(){
        if (instance == null){
            return new AddPriceCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {

        Scanner scanner = new Scanner(System.in);
        MenuUtils.printSeparator();
        System.out.println("Create new price");
        MenuUtils.printSeparator();
        MenuUtils.printPrompt();

        System.out.println("Enter market ID");
        try {
        long marketIdScan = scanner.nextLong();
        if (marketIdScan < 0){
            System.out.println("Market ID can't be negative");
            return AddPriceCommand.getInstance();
        }

        System.out.println("Enter product ID");
        long productIdScan = scanner.nextLong();
        if (productIdScan < 0){
            System.out.println("Market ID can't be negative");
            return AddPriceCommand.getInstance();
        }

        System.out.println("Enter amount (Example 0.00)");
            BigDecimal priceValue = scanner.nextBigDecimal();
            if (priceValue.signum() <= 0) {
                System.out.println("Amount can't be negative");
                return AddPriceCommand.getInstance();
            }
            PricesRepository.getInstance().add(new Price(marketIdScan, productIdScan, priceValue)); /*Вопрос по проверке*/
            MenuUtils.printSeparator();
            System.out.println("Price " + marketIdScan + " - " + productIdScan + " - " + priceValue + " has been created");
        } catch (InputMismatchException a){
            System.out.println("Incorrect number");
            return AddPriceCommand.getInstance();
        }
        return PriceMenuCommand.getInstance();
    }

}
