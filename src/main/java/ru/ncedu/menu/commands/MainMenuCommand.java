package ru.ncedu.menu.commands;

import ru.ncedu.menu.commands.categories.CategoriesMenuCommand;
import ru.ncedu.menu.commands.characteristic.CharacteristicMenuCommand;
import ru.ncedu.menu.commands.characteristicGroups.CharacteristicGroupMenuCommand;
import ru.ncedu.menu.commands.characteristicValues.CharacteristicValuesMenuCommand;
import ru.ncedu.menu.commands.prices.PriceMenuCommand;
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
<<<<<<< HEAD
        MenuUtils.printOption("4", "Characteristic");
        MenuUtils.printOption("5", "Characteristic values");
        MenuUtils.printOption("7", "Priсes");
        MenuUtils.printOption("8", "Save changes");
        MenuUtils.printOption("9", "Discard changes");
=======
        MenuUtils.printOption("4", "Characteristic values");
        MenuUtils.printOption("5", "Characteristic");
        MenuUtils.printOption("6", "Priсes");
        MenuUtils.printOption("7", "Save changes");
        MenuUtils.printOption("8", "Discard changes");
        MenuUtils.printOption("9", "Export to XML");
        MenuUtils.printOption("10", "Import from XML");
>>>>>>> 6c0b1f4680ac67171d484647e121225a2cedf622
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
            case 4:
                return CharacteristicMenuCommand.getInstance();
<<<<<<< HEAD
            case 5:
                return CharacteristicValuesMenuCommand.getInstance();
            case 7:
=======
            case 6:
>>>>>>> 6c0b1f4680ac67171d484647e121225a2cedf622
                return PriceMenuCommand.getInstance();
            case 7:
                return SaveCommand.getInstance();
            case 8:
                return LoadCommand.getInstance();
            case 9:
                return ExportCommand.getInstance();
            case 10:
                return ImportCommand.getInstance();
            default:
                System.out.println("Unexpected command!");
                return this;
        }
    }

    
}
