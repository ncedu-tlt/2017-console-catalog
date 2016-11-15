package ru.ncedu.menu.commands.markets;

import java.util.List;
import java.util.Scanner;
import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Market;
import ru.ncedu.menu.repositories.MarketRepository;
import ru.ncedu.menu.utils.MenuUtils;


public class ViewMarketCommand implements Command {

    private static ViewMarketCommand instance;

    private ViewMarketCommand() {}

    public static synchronized ViewMarketCommand getInstance() {
        if (instance == null) {
            instance = new ViewMarketCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {

        List<Market> listMarkets = MarketRepository.getInstance().get();

        MenuUtils.printSeparator();

        if (listMarkets.isEmpty()) {
            System.out.println("No market have been found");
            return MarketMenuCommand.getInstance();
        }

        for (Market market : listMarkets) {
            MenuUtils.printOption(String.valueOf(market.getId()), market.getName());
        }

        MenuUtils.printSeparator();
        System.out.println("Press Enter to continue");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        return MarketMenuCommand.getInstance();
    }

}