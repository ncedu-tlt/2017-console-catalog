package ru.ncedu.menu.commands.prices;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.Scanner;

public class PriceMenuCommand implements Command{
    private static PriceMenuCommand instance;
    private PriceMenuCommand() {}


    public static synchronized PriceMenuCommand getInstance(){
        if (instance == null) {
            instance = new PriceMenuCommand();
        }
        return instance;
    }

    @Override
    public Command execute(){
        MenuUtils.printSeparator();
        MenuUtils.printOption("1", "Add Prise");
        MenuUtils.printOption("2", "View Prises");
        MenuUtils.printOption("3", "Edit Prise");
        MenuUtils.printOption("4", "Delete Prise");
        MenuUtils.printOption("0", "Back");
        MenuUtils.printSeparator();
        MenuUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 0:
                return MainMenuCommand.getInstance();
            case 1:
                return AddPriceCommand.getInstance();
            case 2:
                return ViewPricesCommand.getInstance();
            case 3:
                return new SelectPriceCommand(new EditPriceCommand());
            case 4:
                return new SelectPriceCommand(new DeletePriceCommand());
            default:
                System.out.println("Unexpected command!");
                return this;
        }
    }
}
