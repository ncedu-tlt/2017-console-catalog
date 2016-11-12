package ru.ncedu.menu.commands.products;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.models.Product;
import ru.ncedu.menu.repositories.CategoriesRepository;
import ru.ncedu.menu.repositories.ProductsRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class SelectProductCommand implements Command {

    private ProductSelectionHandlerCommand selectionHandler;

    public SelectProductCommand(ProductSelectionHandlerCommand selectionHandler) {
        this.selectionHandler = selectionHandler;
    }

    public ProductSelectionHandlerCommand getSelectionHandler() {
        return selectionHandler;
    }

    public void setSelectionHandler(ProductSelectionHandlerCommand selectionHandler) {
        this.selectionHandler = selectionHandler;
    }

    @Override
    public Command execute() {
        List<Category> categories = CategoriesRepository.getInstance().get();
        List<Product> products = ProductsRepository.getInstance().get();

        MenuUtils.printSeparator();

        if (products.isEmpty()) {
            System.out.println("No products have been found");
            return ProductsMenuCommand.getInstance();
        }

        for (Category category : categories) {
            MenuUtils.printCategorySeparator();
            System.out.println("Category name: " + category.getName());
            MenuUtils.printCategorySeparator();
            for (Product product : products) {
                if (product.getCategoryId() == category.getId()) {
                    MenuUtils.printOption(String.valueOf(product.getId()), product.getName());
                }
            }
            MenuUtils.printSeparator();
        }
        MenuUtils.printOption("0", "Back");
        MenuUtils.printSeparator();
        MenuUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        String productId = scanner.next();

        if (productId.equals("0")){
            return ProductsMenuCommand.getInstance();
        }

        try {
            long id = Long.parseLong(productId);
            Product product = ProductsRepository.getInstance().get(id);

            if (product != null){
                selectionHandler.setProduct(product);
                return selectionHandler;
            }

            MenuUtils.printSeparator();
            System.out.println("No product with such ID have been found!");
        }  catch (NumberFormatException e) {
            MenuUtils.printSeparator();
            System.out.println("Product ID should be a number");
        }

        return this;
    }
}
