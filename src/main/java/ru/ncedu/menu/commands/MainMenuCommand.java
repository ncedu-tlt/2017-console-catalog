package ru.ncedu.menu.commands;

import ru.ncedu.menu.commands.characteristicGroups.CharacteristicGroupMenuCommand;
import ru.ncedu.menu.commands.categories.CategoriesMenuCommand;
import ru.ncedu.menu.commands.products.ProductsMenuCommand;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.Scanner;

public class MainMenuCommand implements Command {
    
    private static MainMenuCommand instance;
    
    private MainMenuCommand() {   
    }

    public static synchronized MainMenuCommand getInstance() {
        if (instance == null) {
            instance = new MainMenuCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {

        MenuUtils.printSeparator();
        MenuUtils.printOption("1", "Categories");
        MenuUtils.printOption("2", "Products");
        MenuUtils.printOption("3", "Characteristic groups");
        MenuUtils.printOption("8", "Save changes");
        MenuUtils.printOption("9", "Discard changes");
        MenuUtils.printOption("0", "Exit");
        MenuUtils.printSeparator();
        MenuUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 0:
                return ExitCommand.getInstance();
            case 1:
                return CategoriesMenuCommand.getInstance();
            case 2:
                return ProductsMenuCommand.getInstance();
            case 3:
                return CharacteristicGroupMenuCommand.getInstance();
            case 8:
                return SaveCommand.getInstance();
            case 9:
                return LoadCommand.getInstance();
            default:
                System.out.println("Unexpected command!");
                return this;
        }
    }

    
}
