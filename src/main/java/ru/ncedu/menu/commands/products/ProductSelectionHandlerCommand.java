package ru.ncedu.menu.commands.products;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.models.Product;

abstract class ProductSelectionHandlerCommand implements Command {

    protected Product product;


    protected String handler;

    public ProductSelectionHandlerCommand() {

    }

    public ProductSelectionHandlerCommand(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getHandler() {
        return handler;
    }


}
