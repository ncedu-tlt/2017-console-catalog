package ru.ncedu.menu.commands.characteristic;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Characteristic;
import ru.ncedu.menu.repositories.CharacteristicRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class ViewCharacteristicCommand implements Command {
    private static ViewCharacteristicCommand instance;

    private ViewCharacteristicCommand() {}

    public static synchronized ViewCharacteristicCommand getInstance() {
        if (instance == null) {
            instance = new ViewCharacteristicCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        List<Characteristic> characteristics = CharacteristicRepository.getInstance().get();
        MenuUtils.printSeparator();

        if (characteristics.isEmpty()) {
            System.out.println("Characteristics doen't exist");
            return CharacteristicMenuCommand.getInstance();
        }

        for (Characteristic characteristic : characteristics) {
            MenuUtils.printOption(String.valueOf(characteristic.getId()), characteristic.getName());
        }
        MenuUtils.printSeparator();

        System.out.println("Press enter to continue");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        return CharacteristicMenuCommand.getInstance();
    }
}
