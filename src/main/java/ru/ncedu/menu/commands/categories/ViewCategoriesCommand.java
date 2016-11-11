package ru.ncedu.menu.commands.categories;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.repositories.CategoriesRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class ViewCategoriesCommand implements Command {

    private static ViewCategoriesCommand instance;

    private ViewCategoriesCommand() {}

    public static synchronized ViewCategoriesCommand getInstance() {
        if (instance == null) {
            instance = new ViewCategoriesCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {

        List<Category> categories = CategoriesRepository.getInstance().get();

        MenuUtils.printSeparator();

        if (categories.isEmpty()) {
            System.out.println("No categories have been found");
            return CategoriesMenuCommand.getInstance();
        }

        for (Category category : categories) {
            MenuUtils.printOption(String.valueOf(category.getId()), category.getName());
        }

        MenuUtils.printSeparator();
        System.out.println("Press Enter to continue");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        return CategoriesMenuCommand.getInstance();
    }
}