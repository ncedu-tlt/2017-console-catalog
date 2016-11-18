package ru.ncedu.menu.utils;

import java.util.Scanner;

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

    public static long getLong() {
        long number;
        Scanner scanner = new Scanner(System.in);
        try {
            printPrompt();
            number = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Number is not correct, repeat your entry");
            return getLong();
        }

        return number;
    }

    public static long getId() {
        long id = getLong();
        if (id <= 0) {
            System.out.println("ID can't be negative, repeat your entry");
            return getId();
        }
        return id;
    }
}