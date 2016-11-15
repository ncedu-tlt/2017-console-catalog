package ru.ncedu.menu.commands.prices;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.repositories.PricesRepository;
import ru.ncedu.menu.utils.MenuUtils;

public class DeletePriceCommand extends PriceSelectionHandlerCommand {

    public DeletePriceCommand() {
    }

    @Override
    public Command execute() {
        PricesRepository.getInstance().remove(price);
        MenuUtils.printSeparator();
        System.out.println("Priсe has been deleted");
        return MainMenuCommand.getInstance();
    }


}
