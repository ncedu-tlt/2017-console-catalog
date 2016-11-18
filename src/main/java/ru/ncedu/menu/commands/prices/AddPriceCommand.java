package ru.ncedu.menu.commands.prices;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.markets.AddMarketCommand;
import ru.ncedu.menu.commands.products.AddProductCommand;
import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.models.Market;
import ru.ncedu.menu.models.Price;
import ru.ncedu.menu.models.Product;
import ru.ncedu.menu.repositories.CategoriesRepository;
import ru.ncedu.menu.repositories.MarketRepository;
import ru.ncedu.menu.repositories.PricesRepository;
import ru.ncedu.menu.repositories.ProductsRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AddPriceCommand implements Command {

    private static AddPriceCommand instance;

    private List<Price> prices = PricesRepository.getInstance().get();
    private List<Product> products = ProductsRepository.getInstance().get();
    private List<Market> markets = MarketRepository.getInstance().get();
    private List<Category> categories = CategoriesRepository.getInstance().get();

    private AddPriceCommand() {
    }

    public static synchronized AddPriceCommand getInstance() {
        if (instance == null) {
            return new AddPriceCommand();
        }
        return instance;
    }

    private boolean productsId(long productsId) {
        boolean result = false;
        for (Product product : products) {
            if (product.getId() == productsId) {
                result = true;
            }
        }
        return result;
    }

    private boolean marketId(long marketId) {
        boolean result = false;
        for (Market market : markets) {
            if (market.getId() == marketId) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public Command execute() {
        // TODO: чтобы код был проще, можно декомпозировать метод на несколько
        Scanner scanner = new Scanner(System.in);
        MenuUtils.printSeparator();

        System.out.println("Create new price");
        MenuUtils.printSeparator();

        System.out.println("Enter market ID");
        for (Market market : markets) {
            MenuUtils.printCategorySeparator();
            System.out.println("Market ID: " + market.getId());
            System.out.println("Market name: " + market.getName() + "\n");
        }
        long marketIdScan = MenuUtils.getId();
        if (marketId(marketIdScan)) {
            MenuUtils.printSeparator();
            System.out.println("Market ID found");
            MenuUtils.printSeparator();
        } else {
            MenuUtils.printSeparator();
            System.out.println("Market ID isn't found, create new market");
            return AddMarketCommand.getInstance();
        }

        System.out.println("Enter product ID");
        for (Category category : categories) {
            long categoryId = category.getId();
            for (Product product : products) {
                if (product.getCategoryId() == categoryId) {
                    MenuUtils.printCategorySeparator();
                    System.out.println("Category: " + category.getName());
                    System.out.println("Product ID: " + product.getId());
                    System.out.println("Product name: " + product.getName());
                    System.out.println("Product description: ");
                    System.out.println(product.getDescription() + "\n");

                }
            }
        }
        long productIdScan = MenuUtils.getId();
        if (productsId(productIdScan)) {
            MenuUtils.printSeparator();
            System.out.println("Product ID found");
            MenuUtils.printSeparator();
        } else {
            MenuUtils.printSeparator();
            System.out.println("Product ID isn't found, create new product");
            return AddProductCommand.getInstance();
        }

        System.out.println("Enter amount (Example 0.00)");
        try {
            MenuUtils.printPrompt();
            BigDecimal priceValue = scanner.nextBigDecimal();
            if (priceValue.signum() <= 0) {
                MenuUtils.printSeparator();
                System.out.println("Amount can't be negative");
                return this;
            }
            PricesRepository.getInstance().add(new Price(marketIdScan, productIdScan, priceValue));
            MenuUtils.printSeparator();

            System.out.println("Price: " + "\n" + "Market ID = " + marketIdScan + "\n" + "Product ID = " + productIdScan + "\n" + "Amount = " + priceValue + "\n" + "Has been created");
        } catch (InputMismatchException e) {
            System.out.println("Incorrect number");
            return this;
        }
        return PriceMenuCommand.getInstance();
    }
}
