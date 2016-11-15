package ru.ncedu.menu.commands.markets;

import java.util.Scanner;
import org.apache.commons.lang.StringUtils;
import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.models.Market;
import ru.ncedu.menu.repositories.MarketRepository;
import ru.ncedu.menu.utils.MenuUtils;


public class AddMarketCommand implements Command {
    private static AddMarketCommand instance;

    private AddMarketCommand(){

    }

    public static synchronized AddMarketCommand getInstance() {
        if (instance == null) {
            instance = new AddMarketCommand();
        }
        return instance;
    }

    public Command execute() {

        Scanner scanner = new Scanner(System.in);

        MenuUtils.printSeparator();
        System.out.println("Enter new market name:");
        MenuUtils.printPrompt();

        String marketName = scanner.nextLine();
        String errorMessage = validate(marketName);
        if (errorMessage != null) {
            MenuUtils.printSeparator();
            System.out.println(errorMessage);
            return this;
        }

        MarketRepository.getInstance().add(new Market(marketName));

        MenuUtils.printSeparator();
        System.out.println("Market '" + marketName + "' has been created");

        return MainMenuCommand.getInstance();
    }


    private String validate(String name) {

        if (StringUtils.isEmpty(name)) {
            return "Market name can't be empty";
        }

        return null;
    }
}
