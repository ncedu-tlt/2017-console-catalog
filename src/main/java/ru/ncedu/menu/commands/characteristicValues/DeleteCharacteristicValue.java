package ru.ncedu.menu.commands.characteristicValues;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.models.Characteristic;
import ru.ncedu.menu.models.CharacteristicValue;
import ru.ncedu.menu.repositories.CharacteristicRepository;
import ru.ncedu.menu.repositories.CharacteristicValueRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Alexander on 13.11.2016.
 */
public class DeleteCharacteristicValue extends CharacteristicValueSelectionHandlerCommand{

    public DeleteCharacteristicValue(){
    }

    public DeleteCharacteristicValue(CharacteristicValue characteristicValue) {
        super(characteristicValue);
    }

    @Override
    public Command execute() {
        CharacteristicValueRepository.getInstance().remove(characteristicValue);

        MenuUtils.printSeparator();

        System.out.println("Characteristic values "+characteristicValue.getValue() + " deleted");

        return MainMenuCommand.getInstance();
    }
}
