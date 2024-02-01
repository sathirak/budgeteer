package com.example;

import java.util.Scanner;

public class Main {

    private static void clear_screen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        clear_screen();

        System.out.println("\t\t\u001B[33m################################################################");
        System.out.println("\t\t\u001B[33m#########################  \u001B[43m\u001B[30mBudgeteer\u001B[0m\u001B[33m  ##########################");
        System.out.println("\t\t\u001B[33m################################################################");

        while (true) {

            System.out.println("\n");
            System.out.println("\t\t\u001B[31m                [ 1 ] \u001B[0m Add Log");
            System.out.println("\t\t\u001B[31m                [ 2 ] \u001B[0m Check Log");
            System.out.println("\t\t\u001B[31m                [ 3 ] \u001B[0m Show Log");
            System.out.println("\t\t\u001B[31m                [ 4 ] \u001B[0m Exit");
            System.out.println("\t\t                   ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    clear_screen();
                    AddLog.main(null);
                    break;
                case 2:
                    clear_screen();
                    CheckLog.main(null);
                    break;
                case 3:
                    clear_screen();
                    ShowLog.main(null);
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

}


