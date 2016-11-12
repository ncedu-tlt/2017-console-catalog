package ru.ncedu.menu.commands.products;

import ru.ncedu.menu.commands.Command;

public class SearchProductCommand implements Command {

    private static SearchProductCommand instance;

    public static synchronized SearchProductCommand getInstance(){
        if (instance == null){
            instance = new SearchProductCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        return null;
    }
}
