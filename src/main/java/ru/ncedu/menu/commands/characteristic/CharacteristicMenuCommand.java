package ru.ncedu.menu.commands.characteristic;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.Scanner;


public class CharacteristicMenuCommand implements Command {

    private static CharacteristicMenuCommand instance;


    private CharacteristicMenuCommand() {}

    public static synchronized CharacteristicMenuCommand getInstance() {
        if (instance == null) {
            instance = new CharacteristicMenuCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        MenuUtils.printSeparator();
        MenuUtils.printOption("1", "View characteristics");
        MenuUtils.printOption("2", "Add characteristic");
        MenuUtils.printOption("3", "Edit characteristic");
        MenuUtils.printOption("4", "Delete characteristic");
        MenuUtils.printOption("0", "Back");
        MenuUtils.printSeparator();
        MenuUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 0:
                return MainMenuCommand.getInstance();
            case 1:
                return ViewCharacteristicCommand.getInstance();
            case 2:
                return AddCharacteristicCommand.getInstance();
            case 3:
                return new SelectCharacteristicCommand(new EditCharacteristicCommand());
            case 4:
                return new SelectCharacteristicCommand(new DeleteCharacteristicCommand());
            default:
                System.out.println("Unexcepted command!");
                return this;
        }
    }
}
