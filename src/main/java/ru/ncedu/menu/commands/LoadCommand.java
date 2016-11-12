package ru.ncedu.menu.commands;

import ru.ncedu.menu.repositories.CategoriesRepository;
import ru.ncedu.menu.repositories.ProductsRepository;
import ru.ncedu.menu.utils.MenuUtils;

public class LoadCommand implements Command {

    private static LoadCommand instance;

    private LoadCommand() {
    }

    public static synchronized LoadCommand getInstance() {
        if (instance == null) {
            instance = new LoadCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {

        CategoriesRepository.getInstance().load();
        ProductsRepository.getInstance().load();

        MenuUtils.printSeparator();
        System.out.println("All changes have been discarded");

        return MainMenuCommand.getInstance();
    }
}