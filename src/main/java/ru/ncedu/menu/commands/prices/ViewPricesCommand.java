package ru.ncedu.menu.commands.prices;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.models.Market;
import ru.ncedu.menu.models.Price;
import ru.ncedu.menu.models.Product;
import ru.ncedu.menu.repositories.CategoriesRepository;
import ru.ncedu.menu.repositories.MarketRepository;
import ru.ncedu.menu.repositories.PricesRepository;
import ru.ncedu.menu.repositories.ProductsRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class ViewPricesCommand implements Command {

    private static ViewPricesCommand instance;

    private ViewPricesCommand() {
    }

    public static synchronized ViewPricesCommand getInstance() {
        if (instance == null) {
            instance = new ViewPricesCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        List<Price> prices = PricesRepository.getInstance().get();
        List<Product> products = ProductsRepository.getInstance().get();
        List<Market> markets = MarketRepository.getInstance().get();
        List<Category> categories = CategoriesRepository.getInstance().get();
        MenuUtils.printSeparator();
        if (prices.isEmpty()) {
            System.out.println("Price not found");
            return PriceMenuCommand.getInstance();
        }

        for (Category category : categories) {
            long categoryId = category.getId();
            int iterationCount = 0;
            for (Price price : prices) {
                for (Product product : products) {
                    for (Market market : markets) {
                        if (product.getCategoryId() == categoryId) {
                            if (price.getProductId() == product.getId() && price.getMarketId() == market.getId()) {
                                System.out.println("Category: " + category.getName() + "\n" + "Market ID = " + String.valueOf(price.getMarketId()) + " Name: " + market.getName() + "\n" + "Product ID = " + String.valueOf(price.getProductId()) + " Name: " + product.getName() + "\n" + "Amount: " + String.valueOf(price.getAmount() + "$") + "\n");
                                iterationCount++;
                            }
                        }
                    }
                }
            }
            if (iterationCount == 0) {
                MenuUtils.printSeparator();
                System.out.println("No products in " + category.getName());
                MenuUtils.printSeparator();
            }
        }
        MenuUtils.printSeparator();
        System.out.println("Press Enter to continue");

        MenuUtils.printPrompt();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        return PriceMenuCommand.getInstance();
    }
}
