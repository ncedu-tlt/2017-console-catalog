package ru.ncedu.menu.commands.prices;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.repositories.PricesRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.math.BigDecimal;
import java.util.Scanner;

public class EditPriceCommand extends PriceSelectionHandlerCommand {

    public EditPriceCommand() {
    }

    @Override
    public Command execute() {
        Scanner scanner = new Scanner(System.in);

        MenuUtils.printSeparator();

        System.out.println("Enter new value for maket ID");
        MenuUtils.printPrompt();
        long newMarketId = scanner.nextLong();
        price.setMarketId(newMarketId);

        System.out.println("Enter new value for product ID");
        MenuUtils.printPrompt();
        long newProductId = scanner.nextLong();
        price.setProductId(newProductId);

        System.out.println("Enter new value for amount");
        MenuUtils.printPrompt();
        BigDecimal newValue = scanner.nextBigDecimal(); /*Вопрос по проверке*/
        price.setAmount(newValue);

        PricesRepository.getInstance().update(price);

        MenuUtils.printSeparator();
        System.out.println("Amount has been changed");
        return MainMenuCommand.getInstance();
    }
}
