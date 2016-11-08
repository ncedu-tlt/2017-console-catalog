package ru.ncedu.menu.commands.categories;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.repositories.CategoriesRepository;
import ru.ncedu.menu.utils.MenuUtils;

public class DeleteCategoryCommand extends CategorySelectionHandlerCommand {

    public DeleteCategoryCommand() {
    }

    public DeleteCategoryCommand(Category category) {
        super(category);
    }

    @Override
    public Command execute() {

        CategoriesRepository.getInstance().remove(category);

        MenuUtils.printSeparator();
        System.out.println("Category '" + category.getName() + "' have been deleted");

        return MainMenuCommand.getInstance();
    }
}