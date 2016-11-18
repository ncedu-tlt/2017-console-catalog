package ru.ncedu.menu.commands;

import ru.ncedu.menu.utils.exportutil.ExportController;
import ru.ncedu.menu.utils.exportutil.ExportUtil;
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
        String fileName;
        Scanner scanner = new Scanner(System.in);


        MenuUtils.printCategorySeparator();
        System.out.println("Please Enter path(where do we must save?)");
        MenuUtils.printPrompt();
        path = scanner.nextLine();

        MenuUtils.printCategorySeparator();
        System.out.println("Please Enter file Name.(Not using .XML)");
        MenuUtils.printPrompt();
        fileName = scanner.nextLine();

        if (path.isEmpty() || fileName.isEmpty()) {
            System.out.println("Entered path or file name is not correct. ");
            System.out.println("Press E for Exit, and any key for continue");
            if (scanner.nextLine().equalsIgnoreCase("e")) {
                return MainMenuCommand.getInstance();
            }
        }
        File file = new File(path + "/" + fileName + ".xml");
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
