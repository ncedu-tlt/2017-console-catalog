package ru.ncedu.menu.commands.characteristic;

import com.sun.org.apache.xpath.internal.SourceTree;
import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Characteristic;
import ru.ncedu.menu.repositories.CharacteristicRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class SelectCharacteristicCommand implements Command {
    private CharacteristicSelectionHandlerCommand selectionHandler;

    public SelectCharacteristicCommand() {
    }

    public SelectCharacteristicCommand(CharacteristicSelectionHandlerCommand selectionHandler) {
        this.selectionHandler = selectionHandler;
    }

    public CharacteristicSelectionHandlerCommand getSelectionHandler() {
        return selectionHandler;
    }

    public void setSelectionHandler(CharacteristicSelectionHandlerCommand selectionHandler) {
        this.selectionHandler = selectionHandler;
    }

    @Override
    public Command execute() {
        List<Characteristic> characteristics = CharacteristicRepository.getInstance().get();
        MenuUtils.printSeparator();

        if (characteristics.isEmpty()) {
            System.out.println("Characteristics not found");
            return CharacteristicMenuCommand.getInstance();
        }

        for (Characteristic characteristic : characteristics) {
            MenuUtils.printOption(String.valueOf(characteristic.getId()), characteristic.getName());
        }

        MenuUtils.printOption("0", "Back");
        MenuUtils.printSeparator();
        MenuUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        String characteristicId = scanner.next();

        if (characteristicId.equals("0")) return CharacteristicMenuCommand.getInstance();

        try {
            long id = Long.parseLong(characteristicId);
            Characteristic characteristic = CharacteristicRepository.getInstance().get(id);

            if (characteristic != null) {
                selectionHandler.setCharacteristic(characteristic);
                return selectionHandler;
            }

            MenuUtils.printSeparator();
            System.out.println("Characteristic with such ID not found");
        } catch (NumberFormatException e) {
            MenuUtils.printSeparator();
            System.out.println("Characteristic ID should be a number!");
        }

        return this;
    }
}
