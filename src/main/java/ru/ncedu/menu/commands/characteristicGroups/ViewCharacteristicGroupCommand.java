package ru.ncedu.menu.commands.characteristicGroups;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.characteristicGroups.CharacteristicGroupMenuCommand;
import ru.ncedu.menu.models.CharacteristicGroup;
import ru.ncedu.menu.repositories.CharacteristicGroupRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class ViewCharacteristicGroupCommand implements Command {

    private static ViewCharacteristicGroupCommand instance;

    private ViewCharacteristicGroupCommand() {}

    public static synchronized ViewCharacteristicGroupCommand getInstance() {
        if (instance == null) {
            instance = new ViewCharacteristicGroupCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {

        List<CharacteristicGroup> characteristicGroups = CharacteristicGroupRepository.getInstance().get();

        MenuUtils.printSeparator();

        if (characteristicGroups.isEmpty()) {
            System.out.println("No characteristic groups have been found");
            return CharacteristicGroupMenuCommand.getInstance();
        }

        for (CharacteristicGroup characteristicGroup : characteristicGroups) {
            MenuUtils.printOption(String.valueOf(characteristicGroup.getId()), characteristicGroup.getName());
        }

        MenuUtils.printSeparator();
        System.out.println("Press Enter to continue");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        return CharacteristicGroupMenuCommand.getInstance();
    }
}