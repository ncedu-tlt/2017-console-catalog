package ru.ncedu.menu.commands.products;


import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Product;

public class EditProductCommand extends ProductSelectionHandlerCommand {

    public EditProductCommand(){

    }
    public EditProductCommand(Product product) {super(product);}

    @Override
    public Command execute() {


        return null;
    }
}
