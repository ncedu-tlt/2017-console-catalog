package ru.ncedu.menu.commands;

import java.util.Scanner;

public class MainMenuCommand implements Command {
    
    private static MainMenuCommand instance;
    
    private MainMenuCommand() {   
    }

    public Command execute() {
        System.out.println( "------------" );
        System.out.println( "1 - Add new category" );
        System.out.println( "9 - Main menu" );
        System.out.println( "0 - Exit" );
        System.out.println( "------------" );
                
        Scanner scanner = new Scanner(System.in);
        
        System.out.print( "Enter command: " );
        
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 0:
                return ExitCommand.getInstance();
            case 1:
                return new AddCategoryCommand();
            case 9:
                return MainMenuCommand.getInstance();
            default:
                System.out.println( "Unexpected command!" );
                return new MainMenuCommand();
        }
    }
    
    public static synchronized MainMenuCommand getInstance() {
        if (instance == null) {
                instance = new MainMenuCommand();
        }
        return instance;
    }
    
}
