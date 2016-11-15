package ru.ncedu.menu.commands.characteristic;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Characteristic;

public abstract class CharacteristicSelectionHandlerCommand implements Command {
    protected Characteristic characteristic;

    public CharacteristicSelectionHandlerCommand() {
    }

    public CharacteristicSelectionHandlerCommand(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }
}
