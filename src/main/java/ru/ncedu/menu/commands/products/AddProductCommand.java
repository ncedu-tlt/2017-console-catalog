package ru.ncedu.menu.commands.products;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.categories.AddCategoryCommand;
import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.models.Product;
import ru.ncedu.menu.repositories.CategoriesRepository;
import ru.ncedu.menu.repositories.ProductsRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;


public class AddProductCommand implements Command {

    private static AddProductCommand instance;

    private AddProductCommand() {
    }

    public static synchronized AddProductCommand getInstance() {
        if (instance == null) {
            instance = new AddProductCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        List<Category> categories = CategoriesRepository.getInstance().get();

        long categoryId;
        boolean idIsNotCorrect = true;

        Scanner scanner = new Scanner(System.in);
        MenuUtils.printSeparator();

        if (categories.isEmpty()) {
            System.out.println("Category not found! Create new category" +
                    " before creating products.");
            return AddCategoryCommand.getInstance();
        } else {
            System.out.println("Choice category ID for new product:");
        }

        MenuUtils.printSeparator();
        for (Category category : categories) {
            MenuUtils.printOption(String.valueOf(category.getId()), category.getName());
        }
        MenuUtils.printPrompt();

        do {
            try {
                categoryId = Long.parseLong(scanner.nextLine());

                if (containsId(categoryId, categories)) {
                    idIsNotCorrect = false;
                } else {
                    System.out.println("Entered id is not correct, please enter " +
                            "category ID for product:");
                    MenuUtils.printPrompt();
                }
            } catch (Exception e) {
                System.out.println("Category must be a number.");
                return ProductsMenuCommand.getInstance();
            }
        } while (idIsNotCorrect);

        MenuUtils.printSeparator();
        System.out.println("Enter name for product");
        MenuUtils.printPrompt();

        String productName = scanner.nextLine();

        MenuUtils.printSeparator();
        System.out.println("Enter description for product");
        MenuUtils.printPrompt();

        String productDescription = scanner.nextLine();

        MenuUtils.printSeparator();

        ProductsRepository.getInstance().add(new Product(categoryId, productName, productDescription));

        System.out.println("Product '" + productName + "' has been created");

        return ProductsMenuCommand.getInstance();
    }

    /**
     * Check for product categories
     *
     * @return boolean
     */
    private boolean containsId(long categoryId, List<Category> categories) {
        boolean result = false;

        for (Category category : categories) {
            if (category.getId() == categoryId) {
                result = true;
            }
        }

        return result;
    }
}
