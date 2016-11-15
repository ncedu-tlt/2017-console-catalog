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
        long characterisicId;
        boolean isCorrectCharacteristicId = false;
        boolean isCorrectProductId = false;

        if(characteristics.isEmpty()){
            System.out.println("Characteristic not found. Create a new characteristic: ");
            return AddCharacteristicCommand.getInstance();
        }

        if(products.isEmpty()){
            System.out.println("Product is empty. Create a new product");
            return AddProductCommand.getInstance();
        }

        do{
            System.out.println("(Product id) - Product Name");
            System.out.println("(Characteristic id) - Characteristic Name");

            for(Product product : products){
                for(Characteristic characteristic : characteristics) {
                    MenuUtils.printSeparator();
                    System.out.println("(" + product.getId() + ") - " + product.getName());
                    System.out.println("("+characteristic.getId()+") - " + characteristic.getName());
                }
            }
            MenuUtils.printSeparator();

            System.out.println("Chose product id: ");
            productId = Long.parseLong(scanner.nextLine());

            System.out.println("Chose characteristic id: ");
            characterisicId = Long.parseLong(scanner.nextLine());

            for(Product product : products) {
                for(Characteristic characteristic : characteristics) {
                    isCorrectProductId = isCorrectProductId(product, productId);
                    isCorrectCharacteristicId = isCorrectCharacteristicId(characteristic, characterisicId);
                    if (isCorrectProductId && isCorrectCharacteristicId) break;
                        System.out.println("Your product id is not equal with product.");
                }
            }

        }while(!isCorrectProductId && !isCorrectCharacteristicId);

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
        System.out.println("Value of characteristic \"" + characteristicName + "\" had been created!");

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

    private boolean isCorrectCharacteristicId(Characteristic characteristic, long characteristicId){
        if(characteristic.getId() == characteristicId){
            return true;
        }
        return false;
    }
}
