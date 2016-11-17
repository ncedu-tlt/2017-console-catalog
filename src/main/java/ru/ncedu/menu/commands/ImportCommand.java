package ru.ncedu.menu.commands;

import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.utils.ExportUtil.ExportUtil;
import ru.ncedu.menu.utils.MenuUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
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
        ExportUtil importsCatalog;

        Scanner scanner  = new Scanner(System.in);


        MenuUtils.printCategorySeparator();
        System.out.println("Please Enter path for import file.");

        path = scanner.nextLine();

        if (path.isEmpty()){
            System.out.println("Choice real path to import file!");
            return MainMenuCommand.getInstance();
        }
        File file = new File(path);



        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ExportUtil.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            importsCatalog = (ExportUtil) unmarshaller.unmarshal(file);
            System.out.println("File Export was successful! Path: '" + file.getPath() + "'");
        } catch (Exception e) {
            e.printStackTrace();
            return MainMenuCommand.getInstance();
        }

        List<Category> categories = importsCatalog.getCategoryExport().getCategories();
        for (Category cat : categories){
            System.out.println(cat.getName());
        }

        return MainMenuCommand.getInstance();
    }
}
