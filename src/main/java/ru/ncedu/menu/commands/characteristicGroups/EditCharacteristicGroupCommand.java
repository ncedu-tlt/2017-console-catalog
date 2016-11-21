package ru.ncedu.menu.commands.characteristicGroups;

import org.apache.commons.lang.StringUtils;
import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.CharacteristicGroup;
import ru.ncedu.menu.repositories.CharacteristicGroupRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.Scanner;

public class EditCharacteristicGroupCommand extends CharacteristicGroupSelectionHandlerCommand {

    public EditCharacteristicGroupCommand() {
    }

    public EditCharacteristicGroupCommand(CharacteristicGroup characteristicGroup) {
        super(characteristicGroup);
    }

    @Override
    public Command execute() {

        MenuUtils.printCategorySeparator();
        System.out.println("Characteristic group name is: " + characteristicGroup.getName());
        System.out.println("Characteristic group order number is: " + characteristicGroup.getOrderNumber());

        MenuUtils.printSeparator();
        System.out.println("Choice field for Edit: ");
        MenuUtils.printSeparator();
        MenuUtils.printOption("1", "Edit characteristic group name");
        MenuUtils.printOption("2", "Edit characteristic group order number");
        MenuUtils.printOption("0", "Back");
        MenuUtils.printSeparator();

        int fieldName = (int)MenuUtils.getLong();

        switch (fieldName) {
            case 0:
                return CharacteristicGroupMenuCommand.getInstance();
            case 1:
                nameEdit();
                return this;
            case 2:
                orderNumberEdit();
                return this;
            default:
                System.out.println("Unexpected command!");
                return this;
        }

    }

    /**
     * Validates category name and returns a message if error was found
     *
     * @return Error message
     */
    private String validate(String name) {

        if (StringUtils.isEmpty(name)) {
            return "Characteristic group name can't be empty";
        }

        return null;
    }

    private void nameEdit() {
        System.out.println("Enter new name for characteristicGroup.");

        String characteristicGroupName = new Scanner(System.in).nextLine();

        String errorMessage = validate(characteristicGroupName);
        if (errorMessage != null) {
            MenuUtils.printSeparator();
            System.out.println(errorMessage);
            nameEdit();
        }

        characteristicGroup.setName(characteristicGroupName);
        CharacteristicGroupRepository.getInstance().update(characteristicGroup);

        MenuUtils.printSeparator();
        System.out.println("Characteristic group name have been updated");
    }

    private void orderNumberEdit() {
        System.out.println("Enter order number for characteristicGroup.");
        MenuUtils.printSeparator();

        long orderNumber = MenuUtils.getLong();

        characteristicGroup.setOrderNumber(orderNumber);
        CharacteristicGroupRepository.getInstance().update(characteristicGroup);

        MenuUtils.printSeparator();
        System.out.println("Order number have been updated");
    }

}