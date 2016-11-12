package ru.ncedu.menu.commands.products;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.commands.categories.EditCategoryCommand;
import ru.ncedu.menu.commands.categories.SelectCategoryCommand;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.Scanner;

public class ProductsMenuCommand implements Command {

    private static ProductsMenuCommand instance;

    public static synchronized ProductsMenuCommand getInstance() {
        if (instance == null) {
            instance = new ProductsMenuCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        MenuUtils.printSeparator();
        MenuUtils.printOption("1", "View products");
        MenuUtils.printOption("2", "Search product");
        MenuUtils.printOption("3", "Add product");
        MenuUtils.printOption("4", "Edit product");
        MenuUtils.printOption("5", "Delete product");
        MenuUtils.printOption("0", "Back");
        MenuUtils.printSeparator();
        MenuUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 0:
                return MainMenuCommand.getInstance();
            case 1:
                return ViewProductsCommand.getInstance();
            case 2:
                return SearchProductCommand.getInstance();
            case 3:
                return AddProductCommand.getInstance();
            case 4:
                return new SelectProductCommand(new EditProductCommand());
            case 5:
                return new SelectProductCommand(new DeleteProductCommand());
            default:
                System.out.println("Unexpected command!");
                return this;
        }
    }
}
