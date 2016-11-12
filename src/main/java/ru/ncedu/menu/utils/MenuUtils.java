package ru.ncedu.menu.utils;

public class MenuUtils {

    public static void printSeparator() {
        System.out.println("--------------------------");
    }

    public static void printOption(String command, String name) {
        System.out.println(command + " - " + name);
    }
    public static void printOption(String command, String name, String description) {
        System.out.println(command + " - " + name);
        printSeparator();
        System.out.println(description);
        printSeparator();
    }

    public static void printPrompt() {
        System.out.print("> ");
    }

}