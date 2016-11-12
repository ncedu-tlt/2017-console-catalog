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

        MenuUtils.printSeparator();

        if (products.isEmpty()) {
            System.out.println("No products have been found");
            return ProductsMenuCommand.getInstance();
        }

        for (Category category : categories) {

            long categoryId = category.getId();

            MenuUtils.printOption(String.valueOf(category.getId()), category.getName());
            MenuUtils.printSeparator();

            for (Product product : products) {
                if (product.getId() == categoryId) {
                    MenuUtils.printOption(String.valueOf(product.getId()), product.getName(), product.getDescription());
                }
            }
            MenuUtils.printSeparator();
        }
        System.out.println("Press Enter to continue");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        return ProductsMenuCommand.getInstance();
    }
}
