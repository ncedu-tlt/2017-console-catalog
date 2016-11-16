package ru.ncedu.menu.commands;

import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class ImportCommand implements Command {
    private static ImportCommand instance;

    private ImportCommand() {
    }

    public static synchronized ImportCommand getInstance() {
        if (instance == null) {
            instance = new ImportCommand();
        }
        return instance;
    }


    @Override
    public Command execute() {
        String path;
        List<Object> importObjects;

        Scanner scanner  = new Scanner(System.in);

        MenuUtils.printCategorySeparator();
        System.out.println("Please Enter path for import file.");

        path = scanner.nextLine();

        if (path.isEmpty()){
            System.out.println("Choice real path to import file!");
            return MainMenuCommand.getInstance();
        }



        return MainMenuCommand.getInstance();
    }
}
