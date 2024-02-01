package budget.tracker;

import java.util.Scanner;
import kotlinx.serialization.Serializable;

public class App  {
    private double balance;

    public App (double initialBalance) {
        this.balance = initialBalance;
    }

    public void add_expense(double expenseAmount) {
        balance -= expenseAmount;
        System.out.println("expense of " + expenseAmount + "  added.");
    }

    public void add_income(double incomeAmount) {
        balance += incomeAmount;
        System.out.println("income of " + incomeAmount + " added.");
    }

    public double get_balance() {
        return balance;
    }

    public static void main(String[] args) {

        clearScreen();

        Scanner scanner = new Scanner(System.in);
        System.out.println("\u001B[35mwelcome to budgeter  > \n\n");

        System.out.print("enter your initial balance: LKR >  ");
        double initialBalance = scanner.nextDouble();

        App  budgetTracker = new App (initialBalance);

        boolean continueTracking = true;
        while (continueTracking) {
            
            System.out.println("\n\u001B[34m");
            System.out.println("1. add expense");
            System.out.println("2. add income");
            System.out.println("3. view balance");
            System.out.println("4. exit");
            System.out.print("\nenter your choice  > ");

            int choice = scanner.nextInt();
            clearScreen();
            switch (choice) {
                case 1:
                    System.out.print("enter expense amount: LKR  > ");
                    double expenseAmount = scanner.nextDouble();
                    budgetTracker.add_expense(expenseAmount);
                    break;
                case 2:
                    System.out.print("enter income amount: LKR  > ");
                    double incomeAmount = scanner.nextDouble();
                    budgetTracker.add_income(incomeAmount);
                    break;
                case 3:
                    System.out.println("current Balance: LKR  > " + budgetTracker.get_balance());
                    break;
                case 4:
                    continueTracking = false;
                    break;
                default:
                    System.out.println("invalid choice. please try again  > ");
            }
        }

        System.out.println("thank you for using budgeter  > ");
        scanner.close();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
