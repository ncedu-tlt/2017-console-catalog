package ru.ncedu.menu.commands;

import ru.ncedu.menu.repositories.*;
import ru.ncedu.menu.utils.MenuUtils;

public class SaveCommand implements Command {

    private static SaveCommand instance;

    private SaveCommand() {
    }

    public static synchronized SaveCommand getInstance() {
        if (instance == null) {
            instance = new SaveCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {

        CategoriesRepository.getInstance().save();
        CharacteristicGroupRepository.getInstance().save();
        CharacteristicValueRepository.getInstance().save();
        ProductsRepository.getInstance().save();
        PricesRepository.getInstance().save();
        CharacteristicRepository.getInstance().save();
        MarketRepository.getInstance().save();

        MenuUtils.printSeparator();
        System.out.println("All changes have been saved");

        return MainMenuCommand.getInstance();
    }
}