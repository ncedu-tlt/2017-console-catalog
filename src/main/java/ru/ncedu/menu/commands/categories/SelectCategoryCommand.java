package ru.ncedu.menu.commands.categories;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.repositories.CategoriesRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class SelectCategoryCommand implements Command {

    private CategorySelectionHandlerCommand selectionHandler;

    public SelectCategoryCommand() {
    }

    public SelectCategoryCommand(CategorySelectionHandlerCommand selectionHandler) {
        this.selectionHandler = selectionHandler;
    }

    public CategorySelectionHandlerCommand getSelectionHandler() {
        return selectionHandler;
    }

    public void setSelectionHandler(CategorySelectionHandlerCommand selectionHandler) {
        this.selectionHandler = selectionHandler;
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
        MenuUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        String categoryId = scanner.next();

        try {

            long id = Long.parseLong(categoryId);
            Category category = CategoriesRepository.getInstance().get(id);

            if (category != null) {
                selectionHandler.setCategory(category);
                return selectionHandler;
            }

            MenuUtils.printSeparator();
            System.out.println("No category with such ID have been found!");

        } catch (NumberFormatException e) {
            MenuUtils.printSeparator();
            System.out.println("Category ID should be a number!");
        }

        return this;
    }
}