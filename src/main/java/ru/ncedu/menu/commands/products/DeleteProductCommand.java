package ru.ncedu.menu.commands.products;


import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Product;
import ru.ncedu.menu.repositories.ProductsRepository;
import ru.ncedu.menu.utils.MenuUtils;

public class DeleteProductCommand extends ProductSelectionHandlerCommand {

    public DeleteProductCommand() {
        handler = "Delete";
    }

    public DeleteProductCommand(Product product) {
        super(product);
    }


    @Override
    public Command execute() {

        ProductsRepository.getInstance().remove(product);

        MenuUtils.printSeparator();

        System.out.println("Product '" + product.getName() + "' have been deleted");

        return ProductsMenuCommand.getInstance();
    }
}
