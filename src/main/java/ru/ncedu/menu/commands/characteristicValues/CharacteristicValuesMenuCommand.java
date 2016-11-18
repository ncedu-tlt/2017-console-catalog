package ru.ncedu.menu.commands.characteristicValues;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.Scanner;

/**
 * Created by Alexander on 13.11.2016.
 */
public final class CharacteristicValuesMenuCommand implements Command{

    private static CharacteristicValuesMenuCommand instance;

    // TODO: неприватный конструктор
    CharacteristicValuesMenuCommand(){}

    public static synchronized CharacteristicValuesMenuCommand getInstance(){
        if(instance == null){
            instance = new CharacteristicValuesMenuCommand();
            return instance;
        }
        return instance;
    }



    @Override
    public Command execute() {
        MenuUtils.printSeparator();
        MenuUtils.printOption("1", "View characteristic values");
        MenuUtils.printOption("2", "Add characteristic value");
        MenuUtils.printOption("3", "Edit characteristic value");
        MenuUtils.printOption("4", "Delete characteristic value");
        MenuUtils.printOption("0", "Back");
        MenuUtils.printSeparator();
        MenuUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch(choice){
            case 0:
                return MainMenuCommand.getInstance();
            case 1:
                return ViewCharacteristicValueCommand.getInstance();
            case 2:
                return AddCharacteristicValueCommand.getInstance();
            case 3:
                return new SelectCharacteristicValueCommand(new EditCharacteristicValue());
            case 4:
                return new SelectCharacteristicValueCommand(new DeleteCharacteristicValue());
            default:
                System.out.println("Uknown command");
                return this;
        }

    }
}
