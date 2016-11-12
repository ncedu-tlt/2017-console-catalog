package ru.ncedu.menu.utils;

public class MenuUtils {

    public static void printSeparator() {
        System.out.println("--------------------------");
    }

    public static void printCategorySeparator() {
        System.out.println("==========================");
    }

    public static void printOption(String command, String name) {
        System.out.println(command + " - " + name);
    }

    public static void printPrompt() {
        System.out.print("> ");
    }

}