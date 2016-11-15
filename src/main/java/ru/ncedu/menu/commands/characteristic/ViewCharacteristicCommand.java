package ru.ncedu.menu.commands.characteristic;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.models.Characteristic;
import ru.ncedu.menu.models.CharacteristicGroup;
import ru.ncedu.menu.repositories.CategoriesRepository;
import ru.ncedu.menu.repositories.CharacteristicGroupRepository;
import ru.ncedu.menu.repositories.CharacteristicRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class ViewCharacteristicCommand implements Command {
    List<Category> categories = CategoriesRepository.getInstance().get();
    List<CharacteristicGroup> groups = CharacteristicGroupRepository.getInstance().get();
    private static ViewCharacteristicCommand instance;

    private ViewCharacteristicCommand() {}

    public static synchronized ViewCharacteristicCommand getInstance() {
        if (instance == null) {
            instance = new ViewCharacteristicCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        List<Characteristic> characteristics = CharacteristicRepository.getInstance().get();
        MenuUtils.printSeparator();

        if (characteristics.isEmpty()) {
            System.out.println("Characteristics doen't exist");
            return CharacteristicMenuCommand.getInstance();
        }

        MenuUtils.printCategorySeparator();
        for (Characteristic characteristic : characteristics) {
            System.out.println("Characteristic ID: " + String.valueOf(characteristic.getId()) + "\n" +
                    "Characteristic name: " + characteristic.getName() + "\n" +
                    "Characteristic group: " + iteratorCharacteristicGroup(characteristic.getGroupId()).getName() + "\n"
                    + "Category: " + iteratorCategory(characteristic.getCategoryId()).getName());
            MenuUtils.printCategorySeparator();
        }
        MenuUtils.printSeparator();

        System.out.println("Press enter to continue");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        return CharacteristicMenuCommand.getInstance();
    }

    /**
     *
     * iterator for print category name
     * @return category
     */
    private Category iteratorCategory(long categoryId) {
        for (Category category : categories) {
            if (category.getId() == categoryId) return category;
        }

        return null;
    }

    /**
     *
     * iterator for print characteristic group name
     * @return category
     */
    private CharacteristicGroup iteratorCharacteristicGroup(long characteristicGroupId) {
        for (CharacteristicGroup group : groups) {
            if (group.getId() == characteristicGroupId) return group;
        }

        return null;
    }
}
