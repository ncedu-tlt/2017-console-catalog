package ru.ncedu.menu.commands.products;


import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.models.Product;
import ru.ncedu.menu.repositories.CategoriesRepository;
import ru.ncedu.menu.repositories.ProductsRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class ViewProductsCommand implements Command {

    private static ViewProductsCommand instance;

    private ViewProductsCommand() {
    }

    public static synchronized ViewProductsCommand getInstance() {
        if (instance == null) {
            instance = new ViewProductsCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {

        List<Product> products = ProductsRepository.getInstance().get();
        List<Category> categories = CategoriesRepository.getInstance().get();

        MenuUtils.printCategorySeparator();

        if (products.isEmpty()) {
            System.out.println("No products have been found");
            return ProductsMenuCommand.getInstance();
        }

        for (Category category : categories) {

            long categoryId = category.getId();
            System.out.println("Category ID: " + category.getId());
            System.out.println("Products category name: " + category.getName());
            MenuUtils.printCategorySeparator();
            System.out.println("Products in this category: ");
            MenuUtils.printCategorySeparator();
            int iterationCount = 0;

            for (Product product : products) {
                if (product.getCategoryId() == categoryId) {
                    System.out.println("Product ID: " + product.getId());
                    System.out.println("Product name: " + product.getName());
                    System.out.println("Product description: ");
                    System.out.println(product.getDescription());
                    iterationCount++;
                    MenuUtils.printSeparator();
                }
            }
            if (iterationCount == 0) {
                MenuUtils.printSeparator();
                System.out.println("No products in " + category.getName());
                MenuUtils.printSeparator();
            }
            MenuUtils.printCategorySeparator();
        }
        System.out.println("Press Enter to continue");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        return ProductsMenuCommand.getInstance();
    }
}
