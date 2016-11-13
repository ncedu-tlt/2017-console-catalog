package ru.ncedu.menu.commands.products;


import org.apache.commons.lang.StringUtils;
import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.models.Product;
import ru.ncedu.menu.repositories.CategoriesRepository;
import ru.ncedu.menu.repositories.ProductsRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class EditProductCommand extends ProductSelectionHandlerCommand {

    public EditProductCommand() {
        this.handler = "Edit";
    }

    public EditProductCommand(Product product) {
        super(product);
    }

    @Override
    public Command execute() {
        MenuUtils.printCategorySeparator();
        System.out.println("Product name is: " + product.getName());
        System.out.println("Product category is: " + product.getCategoryId());
        System.out.println("Product description is: " + product.getDescription());


        Scanner scanner = new Scanner(System.in);

        MenuUtils.printSeparator();
        System.out.println("Choice field for Edit: ");
        MenuUtils.printSeparator();
        MenuUtils.printOption("1", "Edit product name");
        MenuUtils.printOption("2", "Edit product category");
        MenuUtils.printOption("3", "Edit product description");
        MenuUtils.printOption("0", "Back");
        MenuUtils.printPrompt();

        int fieldName = Integer.parseInt(scanner.nextLine());
        MenuUtils.printSeparator();
        switch (fieldName) {
            case 0:
                return ProductsMenuCommand.getInstance();
            case 1:
                System.out.println("Please enter new name for product.");
                String productName = null;

                productName = scanner.nextLine();

                String errorMessage = validate(productName);
                if (errorMessage != null) {
                    MenuUtils.printSeparator();
                    System.out.println(errorMessage);
                    return this;
                }

                product.setName(productName);
                ProductsRepository.getInstance().update(product);

                MenuUtils.printSeparator();
                System.out.println("Product name have been updated");
                return ProductsMenuCommand.getInstance();

            case 2:

                System.out.println("Please enter new category for product.");
                MenuUtils.printSeparator();
                printCategory();

                MenuUtils.printSeparator();

                long categoryId = Long.parseLong(scanner.nextLine());

                if (realCategory(categoryId) != null) {
                    MenuUtils.printSeparator();

                    System.out.println(realCategory(categoryId));
                    return this;
                } else {
                    product.setCategoryId(categoryId);

                    ProductsRepository.getInstance().update(product);

                    MenuUtils.printSeparator();

                    System.out.println("Product category have been updated");
                    return ProductsMenuCommand.getInstance();
                }

            case 3:
                System.out.println("Please enter new description");
                String productDescription = null;

                productDescription = scanner.nextLine();

                product.setDescription(productDescription);
                ProductsRepository.getInstance().update(product);

                MenuUtils.printSeparator();
                System.out.println("Product description have been updated");

                return ProductsMenuCommand.getInstance();


        }


        return ProductsMenuCommand.getInstance();
    }

    /**
     * Validates category name and returns a message if error was found
     *
     * @return Error message
     */
    private String validate(String name) {

        if (StringUtils.isEmpty(name)) {
            return "Product name can't be empty";
        }

        return null;
    }

    /**
     * Check the existence of the Category ID  entered
     *
     * @return Error message
     */
    private String realCategory(long categoryId) {
        List<Category> categories = CategoriesRepository.getInstance().get();
        for (Category category : categories) {
            if (category.getId() == categoryId) {
                return null;
            }
        }
        return "Category not found. Please enter real category.";
    }

    /**
     * Print all existing category
     */
    private void printCategory() {
        List<Category> categories = CategoriesRepository.getInstance().get();
        for (Category category : categories) {
            MenuUtils.printOption(String.valueOf(category.getId()), category.getName());
        }
    }
}
