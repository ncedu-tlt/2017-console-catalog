package ru.ncedu.menu.commands.characteristicGroups;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.CharacteristicGroup;
import ru.ncedu.menu.repositories.CharacteristicGroupRepository;
import ru.ncedu.menu.utils.MenuUtils;

public class DeleteCharacteristicGroupCommand extends CharacteristicGroupSelectionHandlerCommand {

    public DeleteCharacteristicGroupCommand() {
    }

    public DeleteCharacteristicGroupCommand(CharacteristicGroup characteristicGroup) {
        super(characteristicGroup);
    }

    @Override
    public Command execute() {

        CharacteristicGroupRepository.getInstance().remove(characteristicGroup);

        MenuUtils.printSeparator();
        System.out.println("Characteristic group '" + characteristicGroup.getName() + "' have been deleted");

        return CharacteristicGroupMenuCommand.getInstance();
    }
}