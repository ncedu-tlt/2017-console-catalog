package ru.ncedu.menu.commands.categories;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.Scanner;

public class CategoriesMenuCommand implements Command {

    private static CategoriesMenuCommand instance;

    private CategoriesMenuCommand() {}

    public static synchronized CategoriesMenuCommand getInstance() {
        if (instance == null) {
            instance = new CategoriesMenuCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {

        MenuUtils.printSeparator();
        MenuUtils.printOption("1", "View categories");
        MenuUtils.printOption("2", "Add category");
        MenuUtils.printOption("3", "Edit category");
        MenuUtils.printOption("4", "Delete category");
        MenuUtils.printOption("0", "Back");
        MenuUtils.printSeparator();
        MenuUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 0:
                return MainMenuCommand.getInstance();
            case 1:
                return ViewCategoriesCommand.getInstance();
            case 2:
                return AddCategoryCommand.getInstance();
            case 3:
                return new SelectCategoryCommand(new EditCategoryCommand());
            case 4:
                return new SelectCategoryCommand(new DeleteCategoryCommand());
            default:
                System.out.println("Unexpected command!");
                return this;
        }
    }
}