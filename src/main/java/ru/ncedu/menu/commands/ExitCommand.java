package ru.ncedu.menu.commands;

public class ExitCommand implements Command {
    
    private ExitCommand() {
    }
    
    private static ExitCommand instance;

    public Command execute() {
        // Exit
        return null;
    }
    
    public static synchronized ExitCommand getInstance() {
        if (instance == null) {
                instance = new ExitCommand();
        }
        return instance;
    }
}
