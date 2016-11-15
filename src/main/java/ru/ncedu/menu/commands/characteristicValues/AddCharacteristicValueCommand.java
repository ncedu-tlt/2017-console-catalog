package ru.ncedu.menu.commands.characteristicValues;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.commands.characteristic.AddCharacteristicCommand;
import ru.ncedu.menu.commands.products.AddProductCommand;
import ru.ncedu.menu.models.Characteristic;
import ru.ncedu.menu.models.CharacteristicGroup;
import ru.ncedu.menu.models.CharacteristicValue;
import ru.ncedu.menu.models.Product;
import ru.ncedu.menu.repositories.CharacteristicGroupRepository;
import ru.ncedu.menu.repositories.CharacteristicRepository;
import ru.ncedu.menu.repositories.CharacteristicValueRepository;
import ru.ncedu.menu.repositories.ProductsRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Alexander on 13.11.2016.
 */
public final class AddCharacteristicValueCommand implements Command {

    private static AddCharacteristicValueCommand instance;

    private AddCharacteristicValueCommand(){}

    public static AddCharacteristicValueCommand getInstance(){
        if(instance==null) {
            return instance = new AddCharacteristicValueCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        List<Characteristic> characteristics = CharacteristicRepository.getInstance().get();
        List<Product> products = ProductsRepository.getInstance().get();

        Scanner scanner = new Scanner(System.in);

        long productId;
        boolean groupIdisCorrect;
        boolean isCorrectId = false;
//        if(characteristics.isEmpty()){
//            System.out.println("Characteristic not found. Create a new characteristic: ");
//            return AddCharacteristicCommand.getInstance();
//        }

        if(products.isEmpty()){
            System.out.println("Product is empty. Create a new product");
            return AddProductCommand.getInstance();
        }

        do{
            for(Product product : products){
                MenuUtils.printSeparator();
                System.out.println(product.getId()+ " - " + product.getName());
            }
            MenuUtils.printSeparator();

            System.out.println("Chose product id: ");
            productId = Long.parseLong(scanner.nextLine());
            for(Product product : products) {
                isCorrectId = isCorrectProductId(product, productId);
                if(isCorrectId) break;
                System.out.println("Your product id is not equal with product.");
            }

        }while(!isCorrectId);

        MenuUtils.printSeparator();
        System.out.println("Add new characteristic value:");
        MenuUtils.printPrompt();

        String characteristicName = scanner.nextLine();
        String error_message = validate(characteristicName); //TODO: используем Camel Case, исключение - константы


        if(error_message != null){
            MenuUtils.printSeparator();
            System.out.println(error_message);
            MenuUtils.printPrompt();
        }

        CharacteristicValueRepository.getInstance().
                add(new CharacteristicValue(productId,characteristicName));;
        MenuUtils.printSeparator();
        System.out.println("Name of characteristic \"" + characteristicName + "\" had been created!");

        return MainMenuCommand.getInstance();
    }

    private String validate(String characteristicName) {
        if(!characteristicName.isEmpty()){
            return "Success!.";
        }
        return null;
    }

    private boolean isCorrectProductId(Product product, long productId){
        if(product.getId() == productId){
            return true;
    }
            return false;
    }
}
