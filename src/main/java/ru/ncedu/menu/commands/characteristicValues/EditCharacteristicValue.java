package ru.ncedu.menu.commands.characteristicValues;

import org.apache.commons.lang.StringUtils;
import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.CharacteristicValue;
import ru.ncedu.menu.repositories.CharacteristicValueRepository;
import ru.ncedu.menu.utils.MenuUtils;
import java.util.Scanner;

/**
 * Created by Alexander on 13.11.2016.
 */
public class EditCharacteristicValue extends CharacteristicValueSelectionHandlerCommand{
    public EditCharacteristicValue() {
    }

    public EditCharacteristicValue(CharacteristicValue characteristicValue) {
        super(characteristicValue);
    }

    @Override
    public Command execute() {
        Scanner scanner = new Scanner(System.in);

        MenuUtils.printSeparator();
        System.out.println("Enter new characteristic value");
        MenuUtils.printPrompt();

        String valueOfCharacteristic = scanner.nextLine();
        String errorMessage = validate(valueOfCharacteristic);

        if(errorMessage != null){
            MenuUtils.printSeparator();
            System.out.println(errorMessage);
            return this;
        }

        characteristicValue.setValue(valueOfCharacteristic);
        CharacteristicValueRepository.getInstance().update(characteristicValue);

        MenuUtils.printSeparator();
        System.out.println("Characteristic value had been change");
        return CharacteristicValuesMenuCommand.getInstance();
    }

    public String validate(String name){
        if(StringUtils.isEmpty(name)){
            return "Name can't be empty!";
        }
        return null;
    }
}
