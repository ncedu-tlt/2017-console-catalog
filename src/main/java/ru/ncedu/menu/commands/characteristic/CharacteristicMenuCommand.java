package ru.ncedu.menu.commands.characteristic;

import ru.ncedu.menu.commands.Command;


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
        return null;
    }
}
