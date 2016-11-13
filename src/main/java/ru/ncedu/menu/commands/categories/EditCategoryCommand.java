package ru.ncedu.menu.commands.categories;

import org.apache.commons.lang.StringUtils;
import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.repositories.CategoriesRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.Scanner;

public class EditCategoryCommand extends CategorySelectionHandlerCommand {

    public EditCategoryCommand() {
    }

    public EditCategoryCommand(Category category) {
        super(category);
    }

    @Override
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

        category.setName(categoryName);
        CategoriesRepository.getInstance().update(category);

        MenuUtils.printSeparator();
        System.out.println("Category have been updated");

        return MainMenuCommand.getInstance();
    }

    /**
     * Validates category name and returns a message if error was found
     *
     * @return Error message
     */
    private String validate(String name) {

        if (StringUtils.isEmpty(name)) {
            return "Category name can't be empty";
        }

        return null;
    }
}