package ru.ncedu.menu.commands;

import ru.ncedu.menu.utils.ExportUtil.ExportController;
import ru.ncedu.menu.utils.ExportUtil.ExportUtil;
import ru.ncedu.menu.utils.MenuUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
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


        MenuUtils.printCategorySeparator();
        System.out.println("Please Enter file Name.(Not using .XML)");

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
        File file = new File("out/" + path + ".xml");

        ExportUtil exportUtil = ExportController.getExportUtil();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ExportUtil.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(exportUtil, file);

            System.out.println("File Export was successful! Path: '" + file.getPath() + "'");
        } catch (Exception e) {
            e.printStackTrace();

        }


        return MainMenuCommand.getInstance();
    }
}
