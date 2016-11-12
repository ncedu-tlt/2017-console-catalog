package ru.ncedu.menu.commands.products;

import org.apache.commons.lang.StringUtils;
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
    List<Category> categories = CategoriesRepository.getInstance().get();
    long categoryId;

    public AddProductCommand() {
    }

    public static synchronized AddProductCommand getInstance() {
        if (instance == null) {
            instance = new AddProductCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {

        boolean idIsNotCorrect = true;
        int count = 0;

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

            categoryId = scanner.nextLong();
            if (containsId(categoryId)) {
                idIsNotCorrect = false;
            } else {
                System.out.println("Entered id is not correct, please enter " +
                        "category ID for product.");
                MenuUtils.printPrompt();
                MenuUtils.printSeparator();
                if (count > 3) {
                    System.out.println("For exit press 0");
                    if (scanner.nextLine().equals("0")) {
                        return ProductsMenuCommand.getInstance();
                    }
                }
            }
            count++;
        } while (idIsNotCorrect);

        MenuUtils.printSeparator();
        System.out.println("Enter name for product");
        MenuUtils.printPrompt();

        String productName = scanner.next();

        MenuUtils.printSeparator();
        System.out.println("Enter description for product");
        MenuUtils.printPrompt();

        String productDescription = scanner.next();

        MenuUtils.printSeparator();


        return null;
    }



    private boolean containsId(long categoryId) {
        boolean result = false;

        for (Category category : categories) {
            if (category.getId() == categoryId) {
                result = true;
            }
        }

        return result;
    }
}
