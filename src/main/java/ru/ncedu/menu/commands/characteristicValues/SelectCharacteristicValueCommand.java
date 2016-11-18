package ru.ncedu.menu.commands.characteristicValues;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.CharacteristicValue;
import ru.ncedu.menu.repositories.CharacteristicValueRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Alexander on 13.11.2016.
 */
public class SelectCharacteristicValueCommand implements Command{

    private CharacteristicValueSelectionHandlerCommand characteristicValueSelectionHandlerCommand;

    public SelectCharacteristicValueCommand() {
    }

    public SelectCharacteristicValueCommand(CharacteristicValueSelectionHandlerCommand characteristicValueSelectionHandlerCommand) {
        this.characteristicValueSelectionHandlerCommand = characteristicValueSelectionHandlerCommand;
    }

    public CharacteristicValueSelectionHandlerCommand getCharacteristicValueSelectionHandlerCommand() {
        return characteristicValueSelectionHandlerCommand;
    }

    public void setCharacteristicValueSelectionHandlerCommand(CharacteristicValueSelectionHandlerCommand characteristicValueSelectionHandlerCommand) {
        this.characteristicValueSelectionHandlerCommand = characteristicValueSelectionHandlerCommand;
    }



    @Override
    public Command execute() {
        List<CharacteristicValue> characteristicValues = CharacteristicValueRepository.getInstance().get();

        MenuUtils.printSeparator();

        if(characteristicValues.isEmpty()){
            System.out.println("No found characteristic values");
            return CharacteristicValuesMenuCommand.getInstance();
        }

        //TODO: неверная логика
        for(CharacteristicValue characteristicValue : characteristicValues){
            MenuUtils.printOption(String.valueOf(characteristicValue.getProductId()),
                    characteristicValue.getValue());
        }

        MenuUtils.printOption("0", "Back");
        MenuUtils.printSeparator();
        MenuUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        String characteristicId = scanner.nextLine();

        if(characteristicId.equals("0")){
            return CharacteristicValuesMenuCommand.getInstance();
        }

        try{
            long id = Long.parseLong(characteristicId);
            CharacteristicValue characteristicValue = CharacteristicValueRepository.getInstance().get(id);

            if(characteristicValue != null){
                characteristicValueSelectionHandlerCommand.setCharacteristicValue(characteristicValue);
                return characteristicValueSelectionHandlerCommand;
            }

            MenuUtils.printSeparator();
            System.out.println("No fonud characteristic with this id");
        }
        catch(NumberFormatException e){
            MenuUtils.printSeparator();
            System.out.println("Characteristic ID it shoud be a number");
        }

        return this;
    }
}
