package ru.ncedu.menu.commands;

import java.util.Scanner;

public class AddCategoryCommand implements Command {

    public Command execute() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print( "Enter new category name: " );
        
        String categoryName = scanner.next();

        // TODO: creation code
        
        System.out.println( "Category '" + categoryName + "' has been created" );
        
        return MainMenuCommand.getInstance();
    }
}
