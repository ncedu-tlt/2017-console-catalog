package ru.ncedu.menu.commands.characteristicGroups;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.models.CharacteristicGroup;

public abstract class CharacteristicGroupSelectionHandlerCommand implements Command {

    protected CharacteristicGroup characteristicGroup;

    public CharacteristicGroupSelectionHandlerCommand() {
    }

    public CharacteristicGroupSelectionHandlerCommand(CharacteristicGroup characteristicGroup) {
        this.characteristicGroup = characteristicGroup;
    }

    public CharacteristicGroup getCharacteristicGroup() {
        return characteristicGroup;
    }

    public void setCharacteristicGroup(CharacteristicGroup characteristicGroup) {
        this.characteristicGroup = characteristicGroup;
    }
}