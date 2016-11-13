package ru.ncedu.menu.commands.characteristicGroups;

import org.apache.commons.lang.StringUtils;
import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.models.CharacteristicGroup;
import ru.ncedu.menu.repositories.CharacteristicGroupRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.Scanner;

public class EditCharacteristicGroupCommand extends CharacteristicGroupSelectionHandlerCommand {

    public EditCharacteristicGroupCommand() {
    }

    public EditCharacteristicGroupCommand(CharacteristicGroup characteristicGroup) {
        super(characteristicGroup);
    }

    @Override
    public Command execute() {

        Scanner scanner = new Scanner(System.in);

        MenuUtils.printSeparator();
        System.out.println("Enter new characteristic group name:");
        MenuUtils.printPrompt();

        String characteristicGroupName = scanner.next();
        String errorMessage = validate(characteristicGroupName);
        if (errorMessage != null) {
            MenuUtils.printSeparator();
            System.out.println(errorMessage);
            return this;
        }

        characteristicGroup.setName(characteristicGroupName);
        CharacteristicGroupRepository.getInstance().update(characteristicGroup);

        MenuUtils.printSeparator();
        System.out.println("Category have been updated");

        return MainMenuCommand.getInstance();
    }

    /**
     * Validates category name and returns a message if error was found
     * @return Error message
     */
    private String validate(String name) {

        if (StringUtils.isEmpty(name)) {
            return "Characteristic group name can't be empty";
        }

        return null;
    }
}