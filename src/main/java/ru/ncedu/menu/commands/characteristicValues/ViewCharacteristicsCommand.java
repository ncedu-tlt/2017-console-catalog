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
public final class ViewCharacteristicsCommand implements Command{

    private static ViewCharacteristicsCommand instance;

    private ViewCharacteristicsCommand(){}

    public static ViewCharacteristicsCommand getInstance(){
        if(instance == null)
            instance = new ViewCharacteristicsCommand();
        return instance;
    }

    @Override
    public Command execute() {
        List<CharacteristicValue> characteristicValues = CharacteristicValueRepository.getInstance().get();

        MenuUtils.printSeparator();

        if(characteristicValues.isEmpty()){
            System.out.println("Not found characteristics int the base.");
            CharacteristicValuesMenuCommand.getInstance();
        }
        for(CharacteristicValue characteristicValue :characteristicValues){
            MenuUtils.printOption(String.valueOf(characteristicValue.getCharacteristicId()),
                    String.valueOf(characteristicValue.getValue()));
        }

        MenuUtils.printSeparator();
        System.out.println("Press enter to continue...");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        return CharacteristicValuesMenuCommand.getInstance();
    }
}
