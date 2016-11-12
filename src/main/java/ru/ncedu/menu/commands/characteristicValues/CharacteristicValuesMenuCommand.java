package ru.ncedu.menu.commands.characteristicValues;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.Scanner;

/**
 * Created by Alexander on 13.11.2016.
 */
public class CharacteristicValuesMenuCommand implements Command{

    private static CharacteristicValuesMenuCommand instance;

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
        MenuUtils.printOption("1", "View characteristics");
        MenuUtils.printOption("2", "Add characteristic");
        MenuUtils.printOption("3", "Edit characteristic");
        MenuUtils.printOption("4", "Delete characteristic");
        MenuUtils.printOption("0", "Back");
        MenuUtils.printSeparator();
        MenuUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch(choice){
            case 0:
                return MainMenuCommand.getInstance();
            case 1:
                return ViewCharacteristicsCommand.getInstance();
            case 2:
                return AddCharacteristicsCommand.getInstance();
            case 3:
//                return SelectCharacteristicCommand.getInstance(new EditCharacteristic);
//            case 4:
//                return SelectCharacteristicCommand.getInstance(new DeleteCharacteristic);
            default:
                System.out.println("Uknown command");
                return this;
        }

    }
}
