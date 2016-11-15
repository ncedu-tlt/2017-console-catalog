package ru.ncedu.menu.commands.markets;

import java.util.Scanner;
import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.utils.MenuUtils;


public class MarketMenuCommand implements Command {

    private static MarketMenuCommand instance;

    private MarketMenuCommand() {}

    public static synchronized MarketMenuCommand getInstance() {
        if (instance == null) {
            instance = new MarketMenuCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {

        MenuUtils.printSeparator();
        MenuUtils.printOption("1", "View markets");
        MenuUtils.printOption("2", "Add market");
        MenuUtils.printOption("3", "Edit market");
        MenuUtils.printOption("4", "Delete market");
        MenuUtils.printOption("0", "Back");
        MenuUtils.printSeparator();
        MenuUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 0:
                return MainMenuCommand.getInstance();
            case 1:
                return ViewMarketCommand.getInstance();
            case 2:
                return AddMarketCommand.getInstance();
            case 3:
                return new SelectMarketCommand(new EditMarketCommand());
            case 4:
                return new SelectMarketCommand(new DeleteMarketCommand());
            default:
                System.out.println("Unexpected command!");
                return this;
        }
    }
}
