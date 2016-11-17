package ru.ncedu.menu.commands.characteristicGroups;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.*;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.Scanner;

public class CharacteristicGroupMenuCommand implements Command {

    private static CharacteristicGroupMenuCommand instance;

    private CharacteristicGroupMenuCommand() {}

    public static synchronized CharacteristicGroupMenuCommand getInstance() {
        if (instance == null) {
            instance = new CharacteristicGroupMenuCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {

        MenuUtils.printSeparator();
        MenuUtils.printOption("1", "View characteristic groups");
        MenuUtils.printOption("2", "Add characteristic group");
        MenuUtils.printOption("3", "Edit characteristic group");
        MenuUtils.printOption("4", "Edit order number");
        MenuUtils.printOption("5", "Delete characteristic group");
        MenuUtils.printOption("0", "Back");
        MenuUtils.printSeparator();
        MenuUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 0:
                return MainMenuCommand.getInstance();
            case 1:
                return ViewCharacteristicGroupCommand.getInstance();
            case 2:
                return AddCharacteristicGroupCommand.getInstance();
            case 3:
                return new SelectCharacteristicGroupCommand(new EditCharacteristicGroupCommand());
            case 4:
                return new SelectCharacteristicGroupCommand(new EditOrderNumberCommand());
            case 5:
                return new SelectCharacteristicGroupCommand(new DeleteCharacteristicGroupCommand());
            default:
                System.out.println("Unexpected command!");
                return this;
        }
    }
}