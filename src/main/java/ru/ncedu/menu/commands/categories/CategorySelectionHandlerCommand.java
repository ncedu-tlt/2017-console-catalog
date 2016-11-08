package ru.ncedu.menu.commands.categories;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Category;

public abstract class CategorySelectionHandlerCommand implements Command {

    protected Category category;

    public CategorySelectionHandlerCommand() {
    }

    public CategorySelectionHandlerCommand(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}