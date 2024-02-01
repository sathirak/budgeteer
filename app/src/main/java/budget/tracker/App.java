package budget.tracker;

import java.util.Scanner;

public class App  {

    private double balance;

    public App (double initial_balance) {
        this.balance = initial_balance;
    }

    public void add_expense(double expense_amount) {
        balance -= expense_amount;
        System.out.println("expense of " + expense_amount + "  added.");
    }

    public void add_income(double income_amount) {
        balance += income_amount;
        System.out.println("income of " + income_amount + " added.");
    }

    public double get_balance() {
        return balance;
    }

    public static void main(String[] args) {

        clear_screen();

        Scanner scanner = new Scanner(System.in);
        System.out.println(" welcome to budgeter  > \n");

        System.out.print(" enter your initial balance: LKR >  ");
        double initial_balance = scanner.nextDouble();

        App  budget_tracker = new App (initial_balance);

        boolean continue_tracking = true;
        while (continue_tracking) {
            
            System.out.println("\n");
            System.out.println("1. add expense");
            System.out.println("2. add income");
            System.out.println("3. view balance");
            System.out.println("4. exit");
            System.out.print("\n   enter your choice  > ");

            int choice = scanner.nextInt();
            clear_screen();
            switch (choice) {
                case 1:
                    System.out.print("enter expense amount: LKR  > ");
                    double expense_amount = scanner.nextDouble();
                    budget_tracker.add_expense(expense_amount);
                    break;
                case 2:
                    System.out.print("enter income amount: LKR  > ");
                    double income_amount = scanner.nextDouble();
                    budget_tracker.add_income(income_amount);
                    break;
                case 3:
                    System.out.println("current Balance: LKR  > " + budget_tracker.get_balance());
                    break;
                case 4:
                    continue_tracking = false;
                    break;
                default:
                    System.out.println("invalid choice. please try again  > ");
            }
        }

        System.out.println("thank you for using budgeter  > ");
        scanner.close();
    }

    private static void clear_screen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    

}
