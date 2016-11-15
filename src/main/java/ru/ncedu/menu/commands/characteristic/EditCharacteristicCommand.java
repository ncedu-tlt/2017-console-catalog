package ru.ncedu.menu.commands.characteristic;

import org.apache.commons.lang.StringUtils;
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

public class EditCharacteristicCommand extends CharacteristicSelectionHandlerCommand {
    List<Category> categories = CategoriesRepository.getInstance().get();
    List<CharacteristicGroup> groups = CharacteristicGroupRepository.getInstance().get();
    public EditCharacteristicCommand() {
    }

    public EditCharacteristicCommand(Characteristic characteristic) {
        super(characteristic);
    }

    @Override
    public Command execute() {
        List<Characteristic> characteristics = CharacteristicRepository.getInstance().get();
        Scanner scanner = new Scanner(System.in);
        MenuUtils.printSeparator();

        MenuUtils.printCategorySeparator();
        System.out.println("Characteristic name: " + characteristic.getName() + "\n" +
                "Characteristic group: " + iteratorCharacteristicGroup(characteristic.getGroupId()).getName() + "\n"
                + "Category: " + iteratorCategory(characteristic.getCategoryId()).getName());
        MenuUtils.printCategorySeparator();

        MenuUtils.printSeparator();

        System.out.println("Choose field for edit: ");
        MenuUtils.printOption("0", "Back");
        MenuUtils.printOption("1", "Edit characteristic name");
        MenuUtils.printOption("2", "Edit characteristic group");
        MenuUtils.printOption("3", "Edit characteristic category");
        MenuUtils.printPrompt();

        int entered = Integer.parseInt(scanner.nextLine());
        MenuUtils.printSeparator();

        switch (entered){
            case 0:
                return CharacteristicMenuCommand.getInstance();
            case 1:
                System.out.println("Please. enter new characteristic name: ");
                String characteristicName = null;

                characteristicName = scanner.nextLine();

                String errorMessage = validate(characteristicName);
                if (errorMessage != null) {
                    MenuUtils.printSeparator();
                    System.out.println(errorMessage);
                    return this;
                }

                characteristic.setName(characteristicName);
                CharacteristicRepository.getInstance().update(characteristic);
                MenuUtils.printSeparator();

                System.out.println("Characteristic name has been changed");
                return CharacteristicMenuCommand.getInstance();
            case 2:
                System.out.println("Please, enter new charasterist group");
                MenuUtils.printSeparator();
                printCharacteristicGroups();
                MenuUtils.printSeparator();

                long characteristicGroupId = Long.parseLong(scanner.nextLine());

                if (realCharacteristicGroup(characteristicGroupId) != null) {
                    MenuUtils.printSeparator();
                    System.out.println(realCharacteristicGroup(characteristicGroupId));
                    return this;
                } else {
                    characteristic.setGroupId(characteristicGroupId);
                    CharacteristicRepository.getInstance().update(characteristic);
                    MenuUtils.printSeparator();

                    System.out.println("Characteristic group has been changed");
                    return CharacteristicMenuCommand.getInstance();
                }
            case 3:
                System.out.println("Please enter new category for characteristic");
                MenuUtils.printSeparator();
                printCategory();
                MenuUtils.printSeparator();

                long categoryId = Long.parseLong(scanner.nextLine());

                if (realCategory(categoryId) != null) {
                    MenuUtils.printSeparator();
                    System.out.println(realCategory(categoryId));
                    return this;
                } else {
                    characteristic.setCategoryId(categoryId);
                    CharacteristicRepository.getInstance().update(characteristic);
                    MenuUtils.printSeparator();

                    System.out.println("Characteristic category has been changed");
                    return CharacteristicMenuCommand.getInstance();
                }
        }

        return CharacteristicMenuCommand.getInstance();
    }

    /**
     * Validates category name and returns a message if error was found
     *
     * @return Error message
     */
    private String validate(String name) {

        if (StringUtils.isEmpty(name)) {
            return "Characteristic can't be empty";
        }

        return null;
    }

    /**
     * Check the existence of the Category ID  entered
     *
     * @return Error message
     */
    private String realCategory(long categoryId) {
        List<Category> categories = CategoriesRepository.getInstance().get();
        for (Category category : categories) {
            if (category.getId() == categoryId) {
                return null;
            }
        }
        return "Category not found. Please enter real category.";
    }

    /**
     * Check the existence of the Category ID  entered
     *
     * @return Error message
     */
    private String realCharacteristicGroup(long characteristicGroupId) {
        List<CharacteristicGroup> groups = CharacteristicGroupRepository.getInstance().get();
        for (CharacteristicGroup group : groups) {
            if (group.getId() == characteristicGroupId) {
                return null;
            }
        }
        return "Characteristic group not found. Please enter real characteristic group.";
    }

    /**
     * Print all existing category
     */
    private void printCategory() {
        List<Category> categories = CategoriesRepository.getInstance().get();
        for (Category category : categories) {
            MenuUtils.printOption(String.valueOf(category.getId()), category.getName());
        }
    }

    /**
     * Print all existing characteristic groups
     */
    private void printCharacteristicGroups() {
        List<CharacteristicGroup> groups = CharacteristicGroupRepository.getInstance().get();
        for (CharacteristicGroup group : groups) {
            MenuUtils.printOption(String.valueOf(group.getId()), group.getName());
        }
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
