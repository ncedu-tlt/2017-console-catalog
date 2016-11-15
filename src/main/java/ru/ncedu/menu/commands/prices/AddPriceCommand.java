package ru.ncedu.menu.commands.prices;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.products.AddProductCommand;
import ru.ncedu.menu.models.Price;
import ru.ncedu.menu.models.Product;
import ru.ncedu.menu.repositories.PricesRepository;
import ru.ncedu.menu.repositories.ProductsRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AddPriceCommand implements Command{

    private static AddPriceCommand instance;

    private List<Price> prices = PricesRepository.getInstance().get();

    private AddPriceCommand() {}

    public static synchronized AddPriceCommand getInstance(){
        if (instance == null){
            return new AddPriceCommand();
        }
        return instance;
    }

    public boolean ProductsId(long productsId){
        boolean result = false;
        List<Product> products = ProductsRepository.getInstance().get();
        for (Product product: products){
            if (product.getId() == productsId) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public Command execute() {
        Scanner scanner = new Scanner(System.in);
        MenuUtils.printSeparator();
        System.out.println("Create new price");
        MenuUtils.printSeparator();
        System.out.println("Enter market ID");
        long marketIdScan = MenuUtils.getId();

        System.out.println("Enter product ID");
        long productIdScan = MenuUtils.getId();
            if (ProductsId(productIdScan) == false){
                System.out.println("Product is not found, create new product");
                return AddProductCommand.getInstance();
            }
        System.out.println("Enter amount (Example 0.00)");
        try {
        BigDecimal priceValue = scanner.nextBigDecimal();
            if (priceValue.signum() <= 0) {
                System.out.println("Amount can't be negative");
                return AddPriceCommand.getInstance();
            }
            PricesRepository.getInstance().add(new Price(marketIdScan, productIdScan, priceValue)); /*Вопрос по проверке*/
            MenuUtils.printSeparator();
            System.out.println("Price " + marketIdScan + " - " + productIdScan + " - " + priceValue + " has been created");
        } catch (InputMismatchException a){
            System.out.println("Incorrect number");
            return AddPriceCommand.getInstance();
        }
        return PriceMenuCommand.getInstance();
    }
}
