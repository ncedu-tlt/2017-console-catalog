package ru.ncedu.menu.commands.characteristicValues;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.models.CharacteristicValue;
import ru.ncedu.menu.repositories.CharacteristicValueRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.Scanner;

/**
 * Created by Alexander on 13.11.2016.
 */
public final class AddCharacteristicsCommand implements Command {

    private static AddCharacteristicsCommand instance;

    private AddCharacteristicsCommand(){}

    public static AddCharacteristicsCommand getInstance(){
        if(instance==null) {
            return instance = new AddCharacteristicsCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        Scanner scanner = new Scanner(System.in);

        MenuUtils.printSeparator();
        System.out.println("Add new characteristic:");
        MenuUtils.printPrompt();

        String characteristicName = scanner.next();
        String error_message = validate(characteristicName); //TODO: используем Camel Case, исключение - константы

        if(error_message != null){
            MenuUtils.printSeparator();
            System.out.println(error_message);
            MenuUtils.printPrompt();
        }

        CharacteristicValueRepository.getInstance().add(new CharacteristicValue(characteristicName));;
        MenuUtils.printSeparator();
        System.out.println("Name of characteristic " + characteristicName + " had been created!");

        return MainMenuCommand.getInstance();
    }

    private String validate(String characteristicName) {
        if(!characteristicName.isEmpty()){
            return "Characteristic name is not empty! Success.";
        }
        return null;
    }
}
