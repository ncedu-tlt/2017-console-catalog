package ru.ncedu.menu.commands.characteristicValues;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.CharacteristicValue;

/**
 * Created by Alexander on 14.11.2016.
 */
public abstract class CharacteristicSelectionHandlerCommand implements Command{

    protected CharacteristicValue characteristicValue;

    public CharacteristicSelectionHandlerCommand() {
    }

    public CharacteristicSelectionHandlerCommand(CharacteristicValue characteristicValue) {
        this.characteristicValue = characteristicValue;
    }

    public CharacteristicValue getCharacteristicValue(){
        return characteristicValue;
    }

    public void setCharacteristicValue(CharacteristicValue characteristicValue){
        this.characteristicValue = characteristicValue;
    }
}
