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
        String path;
        ExportUtil importsCatalog; // TODO: какая-то беда с наименованиями
        boolean updateData = false;

        Scanner scanner = new Scanner(System.in);


        MenuUtils.printCategorySeparator();
        System.out.println("Please Enter path for import file.");
        MenuUtils.printPrompt();
        path = scanner.nextLine();

        if (path.isEmpty()) {
            System.out.println("Choice real path to import file!");
            return this;
        }

        System.out.println("Update Data from XML file? Press Y form update objects in repository, " +
                "and any key for Import without updating.");

        MenuUtils.printPrompt();
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            updateData = true;
        }

        File file = new File(path);


        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ExportUtil.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            importsCatalog = (ExportUtil) unmarshaller.unmarshal(file);
            System.out.println("File Import was successful! Path: '" + file.getPath() + "'");

        } catch (Exception e) {
            e.printStackTrace();
            return MainMenuCommand.getInstance();
        }

        // TODO: сложно :) кроме шуток, можно упростить
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
