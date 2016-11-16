package ru.ncedu.menu.commands;

import ru.ncedu.menu.repositories.*;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExportCommand implements Command {
    private static ExportCommand instance;

    private ExportCommand() {
    }

    public static synchronized ExportCommand getInstance() {
        if (instance == null) {
            instance = new ExportCommand();
        }
        return instance;
    }


    @Override
    public Command execute() {
        String path;
        Scanner scanner = new Scanner(System.in);
        List<Repository> repositories = new ArrayList<>();

        repositories.add(ProductsRepository.getInstance());
        repositories.add(PricesRepository.getInstance());
        repositories.add(MarketRepository.getInstance());
        repositories.add(CharacteristicValueRepository.getInstance());
        repositories.add(CharacteristicRepository.getInstance());
        repositories.add(CharacteristicGroupRepository.getInstance());
        repositories.add(CategoriesRepository.getInstance());



        MenuUtils.printCategorySeparator();
        System.out.println("Please Enter path for Export data.");

        path = scanner.nextLine();

        if (path.isEmpty()) {
            System.out.println("Entered path is not correct. " +
                    "Using DEFAULT path? " +
                    "DEFAULT file path for EXPORT XML: C:\\market.xml");
            System.out.println("Press E for Exit, and any key for continue");
            if (scanner.nextLine().equalsIgnoreCase("e")) {
                return MainMenuCommand.getInstance();
            }
        }

        return MainMenuCommand.getInstance();
    }
}
