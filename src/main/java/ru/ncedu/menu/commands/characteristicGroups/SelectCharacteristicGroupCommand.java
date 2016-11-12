package ru.ncedu.menu.commands.characteristicGroups;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.CharacteristicGroup;
import ru.ncedu.menu.repositories.CharacteristicGroupRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class SelectCharacteristicGroupCommand implements Command {

    private CharacteristicGroupSelectionHandlerCommand selectionHandler;

    public SelectCharacteristicGroupCommand() {
    }

    public SelectCharacteristicGroupCommand(CharacteristicGroupSelectionHandlerCommand selectionHandler) {
        this.selectionHandler = selectionHandler;
    }

    public CharacteristicGroupSelectionHandlerCommand getSelectionHandler() {
        return selectionHandler;
    }

    public void setSelectionHandler(CharacteristicGroupSelectionHandlerCommand selectionHandler) {
        this.selectionHandler = selectionHandler;
    }

    @Override
    public Command execute() {

        List<CharacteristicGroup> categories = CharacteristicGroupRepository.getInstance().get();

        MenuUtils.printSeparator();

        if (categories.isEmpty()) {
            System.out.println("No categories have been found");
            return CharacteristicGroupMenuCommand.getInstance();
        }

        for (CharacteristicGroup characteristicGroup : categories) {
            MenuUtils.printOption(String.valueOf(characteristicGroup.getId()), characteristicGroup.getName());
        }

        MenuUtils.printOption("0", "Back");
        MenuUtils.printSeparator();
        MenuUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        String characteristicGroupId = scanner.next();

        if (characteristicGroupId.equals("0")) {
            return CharacteristicGroupMenuCommand.getInstance();
        }

        try {

            long id = Long.parseLong(characteristicGroupId);
            CharacteristicGroup characteristicGroup = CharacteristicGroupRepository.getInstance().get(id);

            if (characteristicGroup != null) {
                selectionHandler.setCharacteristicGroup(characteristicGroup);
                return selectionHandler;
            }

            MenuUtils.printSeparator();
            System.out.println("No characteristic group with such ID have been found!");

        } catch (NumberFormatException e) {
            MenuUtils.printSeparator();
            System.out.println("Characteristic group ID should be a number!");
        }

        return this;
    }
}