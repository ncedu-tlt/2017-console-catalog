package ru.ncedu.menu.commands.categories;

import org.apache.commons.lang.StringUtils;
import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.repositories.CategoriesRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.Scanner;

public class AddCategoryCommand implements Command {

    private static AddCategoryCommand instance;

    private AddCategoryCommand() {}

    public static synchronized AddCategoryCommand getInstance() {
        if (instance == null) {
            instance = new AddCategoryCommand();
        }
        return instance;
    }

    public Command execute() {

        Scanner scanner = new Scanner(System.in);

        MenuUtils.printSeparator();
        System.out.println("Enter new category name:");
        MenuUtils.printPrompt();

        String categoryName = scanner.nextLine();
        String errorMessage = validate(categoryName);
        if (errorMessage != null) {
            MenuUtils.printSeparator();
            System.out.println(errorMessage);
            return this;
        }

        CategoriesRepository.getInstance().add(new Category(categoryName));

        MenuUtils.printSeparator();
        System.out.println("Category '" + categoryName + "' has been created");
        
        return MainMenuCommand.getInstance();
    }

    /**
     * Validates category name and returns a message if error was found
     * @return Error message
     */
    private String validate(String name) {

        if (StringUtils.isEmpty(name)) {
            return "Category name can't be empty";
        }

        return null;
    }
}
