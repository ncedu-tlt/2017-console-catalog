package ru.ncedu.menu.commands.characteristicValues;

import ru.ncedu.menu.commands.Command;
import ru.ncedu.menu.commands.MainMenuCommand;
import ru.ncedu.menu.models.CharacteristicValue;
import ru.ncedu.menu.models.Product;
import ru.ncedu.menu.repositories.CharacteristicValueRepository;
import ru.ncedu.menu.repositories.ProductsRepository;
import ru.ncedu.menu.utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Alexander on 13.11.2016.
 */

public final class ViewCharacteristicValueCommand implements Command{

    private static ViewCharacteristicValueCommand instance;
    private String productName;
    private ViewCharacteristicValueCommand(){}

    public static ViewCharacteristicValueCommand getInstance(){
        if(instance == null)
            instance = new ViewCharacteristicValueCommand();
        return instance;
    }

    @Override
    public Command execute() {
        List<CharacteristicValue> characteristicValues = CharacteristicValueRepository.getInstance().get();

        MenuUtils.printSeparator();

        if(characteristicValues.isEmpty()){
            System.out.println("Not found characteristics int the base.");
            CharacteristicValuesMenuCommand.getInstance();
        }

        for(CharacteristicValue characteristicValue : characteristicValues){

            productName = getProduct(characteristicValues);

            if(productName != null) {
                MenuUtils.printOption(productName,
                        String.valueOf(characteristicValue.getValue()));
            }
            else{
                System.out.println("Uknown error");
                return MainMenuCommand.getInstance();
            }
        }

        MenuUtils.printSeparator();
        System.out.println("Press enter to continue...");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        return CharacteristicValuesMenuCommand.getInstance();
    }

    private String getProduct(List<CharacteristicValue> characteristicValue){

        List <Product> products = ProductsRepository.getInstance().get();
        for(Product product : products){
            for(CharacteristicValue value : characteristicValue){
                if(product.getId() == value.getProductId()){
                    return "Product \""+product.getName()+"\" ";
                }
            }
        }
        return null;
    }
}
