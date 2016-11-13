package ru.ncedu.menu.commands.categories;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.models.Product;
import ru.ncedu.menu.repositories.CategoriesRepository;
import ru.ncedu.menu.repositories.ProductsRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class DeleteCategoryCommand extends CategorySelectionHandlerCommand {

    List<Product> products = ProductsRepository.getInstance().get();

    public DeleteCategoryCommand() {
    }

    public DeleteCategoryCommand(Category category) {
        super(category);
    }

    @Override
    public Command execute() {


        Scanner scanner = new Scanner(System.in);


        if (inspectionProductCategory() != null){
            System.out.println(inspectionProductCategory());
            System.out.println("Enter 'D' for delete category and" +
                    " products, and any key for return to menu.");
            if (!(scanner.nextLine().equalsIgnoreCase("d"))){
                return MainMenuCommand.getInstance();
            }
        }

        CategoriesRepository.getInstance().remove(category);


        MenuUtils.printSeparator();
        System.out.println("Category '" + category.getName() + "' have been deleted");

        return MainMenuCommand.getInstance();
    }
    /**
     * Find product contains in category.
     * @return Warning message
     */
    private String inspectionProductCategory() {

        int productCount = 0;

        for (Product product : products) {
            if (product.getCategoryId() == category.getId()) {
                productCount++;
            }
        }

        return productCount > 0 ? "Category is contains " + productCount +
                " product(s).  All products is contained in this category" +
                " will been deleted. Delete this category?" : null;
    }
}
