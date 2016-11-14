package ru.ncedu.menu.commands.characteristicGroups;

import org.apache.commons.lang.StringUtils;
import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.models.CharacteristicGroup;
import ru.ncedu.menu.repositories.CharacteristicGroupRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.Scanner;

public class AddCharacteristicGroupCommand implements Command {

    private static AddCharacteristicGroupCommand instance;

    private AddCharacteristicGroupCommand() {}

    public static synchronized AddCharacteristicGroupCommand getInstance() {
        if (instance == null) {
            instance = new AddCharacteristicGroupCommand();
        }
        return instance;
    }

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

        CharacteristicGroupRepository.getInstance().add(new CharacteristicGroup(characteristicGroupName));

        MenuUtils.printSeparator();
        System.out.println("Characteristic group '" + characteristicGroupName + "' has been created");
        
        return CharacteristicGroupMenuCommand.getInstance();
    }

    /**
     * Validates characteristicGroup name and returns a message if error was found
     * @return Error message
     */
    private String validate(String name) {

        if (StringUtils.isEmpty(name)) {
            return "Characteristic group name can't be empty";
        }

        return null;
    }
}
