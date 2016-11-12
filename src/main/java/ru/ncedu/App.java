package ru.ncedu;

import ru.ncedu.menu.ConsoleMenu;
import ru.ncedu.menu.commands.MainMenuCommand;

public class App 
{
    public static void main( String[] args )
    {
        // test
        ConsoleMenu menu = new ConsoleMenu(MainMenuCommand.getInstance());
        menu.run();
    }
}
