package ru.ncedu.menu.commands;

import ru.ncedu.menu.utils.MenuUtils;
import ru.ncedu.menu.utils.exportutil.ExportUtil;
import ru.ncedu.menu.utils.exportutil.importutils.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
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
        ExportUtil importsCatalog;
        boolean updateData = false;

        Scanner scanner = new Scanner(System.in);


        MenuUtils.printCategorySeparator();
        System.out.println("Please Enter import path(where do we must load?)");
        MenuUtils.printPrompt();
        String path = scanner.nextLine();

        // Сюда нужен какой-то способ вывода в консоль имеющихся файлов в path

        MenuUtils.printCategorySeparator();
        System.out.println("Please Enter file name.(Not using .XML)");
        MenuUtils.printPrompt();
        String fileName = scanner.nextLine();

        if (path.isEmpty()) {
            System.out.println("Entered path or file name is incorrect. Please try again");
            return this;
        }

        System.out.println("Update Data from XML file? Press Y form update objects in repository, " +
                "and any key for Import without updating.");

        MenuUtils.printPrompt();
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            updateData = true;
        }

        File file = new File(path + "/" + fileName + ".xml");


        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ExportUtil.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            importsCatalog = (ExportUtil) unmarshaller.unmarshal(file);
            System.out.println("File Import was successful! Path: '" + file.getPath() + "'");

        } catch (Exception e) {
            e.printStackTrace();
            return MainMenuCommand.getInstance();
        }
        new CategoryImportUtil(importsCatalog.getCategoryExport().getCategories(), updateData).setInRepository();
        new CharacteristicGroupImportUtil(importsCatalog.getCharacteristicGroupExport().getCharacteristicGroups(), updateData).setInRepository();
        new CharacteristicImportUtil(importsCatalog.getCharacteristicExport().getCharacteristics(), updateData).setInRepository();
        new CharacteristicValueImportUtil(importsCatalog.getCharacteristicValueExport().getCharacteristicValues(), updateData).setInRepository();
        new MarketImportUtil(importsCatalog.getMarketExport().getMarkets(), updateData).setInRepository();
        new PricesImportUtil(importsCatalog.getPriceExport().getPrices(), updateData).setInRepository();
        new ProductsImportUtil(importsCatalog.getProductExport().getProducts(), updateData).setInRepository();


        return MainMenuCommand.getInstance();
    }

}
