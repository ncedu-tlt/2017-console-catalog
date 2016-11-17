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
        long characterisicId = 0;
        boolean isCorrectCharacteristicId;
        boolean isCorrectProductId;

        if(characteristics.isEmpty()){
            return AddCharacteristicCommand.getInstance();
        }

        if(products.isEmpty()){
            return AddProductCommand.getInstance();
        }

        do{

            isCorrectCharacteristicId= false;
            isCorrectProductId = false;

            for(Product product : products){
                System.out.println("Product: \n(" + product.getId() + ") - " + product.getName());
                MenuUtils.printSeparator();
                for(Characteristic characteristic : characteristics) {
                    if(characteristic != null)
                        System.out.println("Characteristic: ("+characteristic.getId()+") - " + characteristic.getName());
                    else
                        System.out.println("No found characteristic");
                }
                MenuUtils.printSeparator();
            }


            System.out.println("Chose product id: ");
            MenuUtils.printPrompt();
            productId = Long.parseLong(scanner.nextLine());
            for(Product product : products){
                isCorrectProductId = isCorrectProductId(product, productId);
                if(!isCorrectProductId){
                    break;
                }
                else{
                    continue;
                }
            }
                System.out.println("Chose characteristic id: ");
                MenuUtils.printPrompt();
                characterisicId = Long.parseLong(scanner.nextLine());
                for (Characteristic characteristic : characteristics) {
                    isCorrectCharacteristicId = isCorrectCharacteristicId(characteristic, characterisicId);
                }
                if (!isCorrectCharacteristicId) {
                    continue;
                } else {
                    break;
                }
        }while(!isCorrectProductId || !isCorrectCharacteristicId);

        MenuUtils.printSeparator();
        System.out.println("Add new characteristic value:");
        MenuUtils.printPrompt();

        String characteristicValue = scanner.nextLine();
        String error_message = validate(characteristicValue); //TODO: используем Camel Case, исключение - константы


        if(error_message != null){
            MenuUtils.printSeparator();
            System.out.println(error_message);
            MenuUtils.printPrompt();
        }

        CharacteristicValueRepository.getInstance().
                add(new CharacteristicValue(productId, characterisicId,characteristicValue));;
        MenuUtils.printSeparator();
        System.out.println("Value of characteristic \"" + characteristicValue + "\" had been created!");

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
