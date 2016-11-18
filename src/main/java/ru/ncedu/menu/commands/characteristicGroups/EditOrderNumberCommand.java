package ru.ncedu.menu.commands.characteristicGroups;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.CharacteristicGroup;
import ru.ncedu.menu.repositories.CharacteristicGroupRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.Scanner;

public class EditOrderNumberCommand extends CharacteristicGroupSelectionHandlerCommand {

    public EditOrderNumberCommand() {
    }

    public EditOrderNumberCommand(CharacteristicGroup characteristicGroup) {
        super(characteristicGroup);
    }

    @Override
    public Command execute() {

        // TODO: можно было реализовать в одной команде с редактированием имени

        Scanner scanner = new Scanner(System.in);

        MenuUtils.printSeparator();
        System.out.println("Enter order number for characteristic group:");

        long orderNumber = MenuUtils.getLong();

        characteristicGroup.setOrderNumber(orderNumber);
        CharacteristicGroupRepository.getInstance().update(characteristicGroup);

        MenuUtils.printSeparator();
        System.out.println("Order number have been updated");

        return CharacteristicGroupMenuCommand.getInstance();
    }

}