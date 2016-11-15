package ru.ncedu.menu.commands.characteristic;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.models.Characteristic;
import ru.ncedu.menu.models.CharacteristicValue;
import ru.ncedu.menu.repositories.CharacteristicRepository;
import ru.ncedu.menu.repositories.CharacteristicValueRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class DeleteCharacteristicCommand extends CharacteristicSelectionHandlerCommand {
    List<CharacteristicValue> characteristicValues = CharacteristicValueRepository.getInstance().get();

    public DeleteCharacteristicCommand() {
    }

    public DeleteCharacteristicCommand(Characteristic characteristic) {
        super(characteristic);
    }

    @Override
    public Command execute() {
        Scanner scanner = new Scanner(System.in);

        if (inspectionCharacteristicValue() != null){
            System.out.println(inspectionCharacteristicValue());
            System.out.println("Enter 'd' for delete characteristic and" +
                    " characteristic values, and any key for return to menu.");
            if (!(scanner.nextLine().equalsIgnoreCase("d"))){
                return MainMenuCommand.getInstance();
            }
        }

        CharacteristicRepository.getInstance().remove(characteristic);
        MenuUtils.printSeparator();

        System.out.println("Characteristic " + characteristic.getName() + " has been deleted");

        return MainMenuCommand.getInstance();
    }

    /**
     * Find characteristic value contains in characteristic
     * @return Warning message
     */
    private String inspectionCharacteristicValue() {
        int valueCount = 0;

        for (CharacteristicValue characteristicValue: characteristicValues) {
            if (characteristicValue.getCharacteristicId() == characteristic.getId()) {
                valueCount++;
            }
        }

        return valueCount > 0 ? "Characteristic is contains " + valueCount +
                "characteristic values. All characteristic values are contained in this characteristic" +
                "will been deleted. Delete this characteristic?" : null;
    }
}
