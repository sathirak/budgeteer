package com.example;

import java.util.Scanner;

public class Main {

    static void clear_screen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Budgeteer budgeteer = new Budgeteer();

        boolean menu_on = true;

        while (menu_on) {

            clear_screen();

            System.out.println("\u001B[1m\u001B[33m\u001B[4m[   Budgeteer   ]\u001B[0m");
            System.out.println("\u001B[30m   #by sekisaii");

            System.out.println("\n");
            System.out.println("\t\u001B[31m  [ 1 ] \u001B[0m Add Entry");
            System.out.println("\t\u001B[31m  [ 2 ] \u001B[0m Stats");
            System.out.println("\t\u001B[31m  [ 3 ] \u001B[0m Show All Logs");
            System.out.println("\t\u001B[31m  [ 4 ] \u001B[0m Search Entry");
            System.out.println("\t\u001B[31m  [ 5 ] \u001B[0m Filter Logs");
            System.out.println("\t\u001B[31m  [ 6 ] \u001B[0m Delete Logs");

            System.out.println("\n\t\u001B[31m  [ 0 ] \u001B[0m Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:

                    clear_screen();
                    budgeteer.addLog();
                    break;
                case 2:

                    clear_screen();
                    budgeteer.reportLog();
                    break;
                case 3:

                    clear_screen();
                    budgeteer.showLog();
                    break;
                case 4:

                    clear_screen();
                    budgeteer.checkLog();
                    break;
                case 5:

                    clear_screen();
                    budgeteer.scanLog();
                    break;
                case 6:

                    clear_screen();
                    budgeteer.deleteLog();
                    break;
                case 0:

                    clear_screen();
                    System.out.println("\u001b[33m[ ! ]\u001B[0m Exiting the program. Goodbye!");
                    menu_on = false;
                    break;
                default:
                    System.out.println("\u001b[33m[ ! ]\u001B[0m Invalid choice. Please enter a valid option.");
            }

            System.out.print("\n\n\u001B[36m << [Enter]\u001B[0m");
            scanner.nextLine();
        }

        scanner.close();
    }


}
