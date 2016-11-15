package ru.ncedu.menu.commands.prices;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.repositories.PricesRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EditPriceCommand extends PriceSelectionHandlerCommand {

    public EditPriceCommand() {
    }

    @Override
    public Command execute() {
        Scanner scanner = new Scanner(System.in);

        MenuUtils.printSeparator();

        /*System.out.println("Enter new value for market ID");
        long newMarketId = MenuUtils.getId();
        price.setMarketId(newMarketId);

        System.out.println("Enter new value for product ID");
        long newProductId = MenuUtils.getId();
        price.setProductId(newProductId);*/

        System.out.println("Enter new value for amount");
        MenuUtils.printPrompt();
        try {
            BigDecimal newValue = scanner.nextBigDecimal();
            if (newValue.signum() <= 0) {
                System.out.println("Amount can't be negative");
                return this;
            }
            price.setAmount(newValue);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect number");
            return this;
        }

        PricesRepository.getInstance().update(price);

        MenuUtils.printSeparator();
        System.out.println("Amount has been changed");
        return PriceMenuCommand.getInstance();
    }
}
