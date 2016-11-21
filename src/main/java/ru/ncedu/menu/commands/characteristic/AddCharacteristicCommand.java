package ru.ncedu.menu.commands.characteristic;

import org.apache.commons.lang.StringUtils;
import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.commands.categories.AddCategoryCommand;
import ru.ncedu.menu.commands.characteristicGroups.AddCharacteristicGroupCommand;
import ru.ncedu.menu.commands.characteristicGroups.CharacteristicGroupMenuCommand;
import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.models.Characteristic;
import ru.ncedu.menu.models.CharacteristicGroup;
import ru.ncedu.menu.repositories.CategoriesRepository;
import ru.ncedu.menu.repositories.CharacteristicGroupRepository;
import ru.ncedu.menu.repositories.CharacteristicRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class AddCharacteristicCommand implements Command {
    private static AddCharacteristicCommand instance;

    private AddCharacteristicCommand() {
    }

    public static synchronized AddCharacteristicCommand getInstance() {
        if (instance == null) {
            instance = new AddCharacteristicCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        List<CharacteristicGroup> groups = CharacteristicGroupRepository.getInstance().get();
        List<Category> categories = CategoriesRepository.getInstance().get();

        long groupId, categoryId;
        boolean isIdGroupNotCorrect = true;
        boolean isIdCategoryNotCorrect = true;

        Scanner scanner = new Scanner(System.in);

        // TODO: здесь нужно команды делать контекстно-завсимыми, чтобы после создания вернуть пользователя обратно
        if (categories.isEmpty()) {
            System.out.println("Category doesn't exist. " +
                    "Please create new category before creating characteristic.");
            return AddCategoryCommand.getInstance();
        }
        if (groups.isEmpty()) {
            System.out.println("Characteristic group doesn't exist. " +
                    "Please create new characteristic group before creating characteristic.");
            //Устанавливаем AddCharacteristicCommand как следующую команду после AddCharacteristicGroupCommand
            AddCharacteristicGroupCommand.getInstance().setNextCommand(this);
            return AddCharacteristicGroupCommand.getInstance();
        }
        //Возвращаем значение следующей команды на AddCharacteristicGroupCommand
        AddCharacteristicGroupCommand.getInstance().setNextCommand(CharacteristicGroupMenuCommand.getInstance());

        MenuUtils.printSeparator();

        System.out.println("Choose category ID for new characteristic:");

        for (Category category : categories) {
            MenuUtils.printOption(String.valueOf(category.getId()), category.getName());
        }
        MenuUtils.printPrompt();

        do {
            try {
                categoryId = Long.parseLong(scanner.nextLine());

                if (isCategoryIdCorrect(categoryId, categories)) isIdCategoryNotCorrect = false;
                else System.out.println("Entered category ID doen't exist, please enter correct id:");
            } catch (Exception e) {
                System.out.println("Category ID must be a number.");
                return CharacteristicMenuCommand.getInstance();
            }
        } while (isIdCategoryNotCorrect);
        MenuUtils.printSeparator();

        System.out.println("Choose characteristic group ID for new characteristic:");

        for (CharacteristicGroup group : groups) {
            MenuUtils.printOption(String.valueOf(group.getId()), group.getName());
        }
        MenuUtils.printPrompt();

        do {
            try {
                groupId = Long.parseLong(scanner.nextLine());

                if (isGroupIdCorrect(groupId, groups)) isIdGroupNotCorrect = false;
                else System.out.println("Entered characteristic group ID doen't exist, please enter correct id:");
            } catch (Exception e) {
                System.out.println("Characteristic group ID must be a number.");
                return CharacteristicMenuCommand.getInstance();
            }
        } while (isIdGroupNotCorrect);
        MenuUtils.printSeparator();

        System.out.println("Please, enter new characteristic name:");
        MenuUtils.printPrompt();

        String enteredName = scanner.nextLine();
        String errorMessage = validate(enteredName);
        if (errorMessage != null) {
            MenuUtils.printSeparator();
            System.out.println(errorMessage);
            return this;
        }

        CharacteristicRepository.getInstance().add(new Characteristic(enteredName, categoryId, groupId));
        MenuUtils.printSeparator();

        System.out.println("Characteristic '" + enteredName + "' has been created.");

        return MainMenuCommand.getInstance();
    }

    /**
     * Check characteristic group ID exists
     *
     * @return boolean
     */
    private boolean isGroupIdCorrect(long groupID, List<CharacteristicGroup> groups) {
        boolean result = false;

        for (CharacteristicGroup group : groups) {
            if (group.getId() == groupID) result = true;
        }

        return result;
    }

    /**
     * Check category ID exists
     *
     * @return boolean
     */
    private boolean isCategoryIdCorrect(long groupID, List<Category> categories) {
        boolean result = false;

        for (Category category : categories) {
            if (category.getId() == groupID) result = true;
        }

        return result;
    }

    /**
     * Validates category name and returns a message if error was found
     *
     * @return Error message
     */
    private String validate(String name) {

        if (StringUtils.isEmpty(name)) {
            return "Characteristic name can't be empty";
        }

        return null;
    }
}
