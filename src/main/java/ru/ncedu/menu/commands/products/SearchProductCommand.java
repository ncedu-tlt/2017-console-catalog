package ru.ncedu.menu.commands.products;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.models.Product;
import ru.ncedu.menu.repositories.CategoriesRepository;
import ru.ncedu.menu.repositories.ProductsRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchProductCommand implements Command {

    private static SearchProductCommand instance;

    private SearchProductCommand(){}

    private List<Product> products = ProductsRepository.getInstance().get();
    private List<Category> categories = CategoriesRepository.getInstance().get();

    public static synchronized SearchProductCommand getInstance() {
        if (instance == null) {
            instance = new SearchProductCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        boolean inExit = false;
        Scanner scanner = new Scanner(System.in);

        if (products.isEmpty()) {
            System.out.println("No products have been found");
            return ProductsMenuCommand.getInstance();
        }

        do {
            System.out.println("Please enter the name of a product for found:");
            MenuUtils.printPrompt();
            String searchProduct = scanner.next();

            List<Product> searchResult = searchProducts(searchProduct);

            System.out.println("Search result:");
            if (searchResult.isEmpty()) {
                MenuUtils.printSeparator();
                System.out.println("The search has not given any results");
                MenuUtils.printSeparator();
            } else {
                for (Product product : searchResult) {
                    MenuUtils.printCategorySeparator();
                    System.out.println("Category product: "
                            + getCategoryName(product.getCategoryId()));
                    MenuUtils.printCategorySeparator();
                    System.out.println("Product name: " + product.getName());
                    System.out.println("Product ID: " + product.getId());
                    System.out.println("Description: " + product.getDescription());
                }
            }

            MenuUtils.printCategorySeparator();
            System.out.println("Press 'S' for new search" +
                    " or any key for exit.");
            MenuUtils.printPrompt();
            if (scanner.next().equalsIgnoreCase("S")) {
                inExit = false;
            } else {
                inExit = true;
            }

        } while (!inExit);
        return ProductsMenuCommand.getInstance();
    }
    /**
     *  Search products in repository.
     *
     */
    private List<Product> searchProducts(String productName) {

        List<Product> searchResult = new ArrayList<>();
        Pattern pattern = Pattern.compile(".*" + productName + ".*");
        Matcher matcher;

        for (Product product : products) {
            matcher = pattern.matcher(product.getName());
            if (matcher.matches()) {
                searchResult.add(product);
            }
        }
        return searchResult;
    }
    /**
     * Search category name by category ID.
     *
     * @return Category name
     */
    private String getCategoryName(long categoryId) {
        for (Category category : categories) {
            if (category.getId() == categoryId) {
                return category.getName();
            }
        }
        return null;
    }
}
