package ru.ncedu.menu;

import ru.ncedu.menu.commands.Command;

public class ConsoleMenu {
    
    private Command command;
    
    public ConsoleMenu(Command startCommand) {
        this.command = startCommand;
    }
    
    public void run() {
        while (command != null) {
            command = command.execute();
        }
    }
    
}
