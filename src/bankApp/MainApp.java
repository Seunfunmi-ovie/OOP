package bankApp;

import java.util.Scanner;

public class MainApp {
    private static Scanner input = new Scanner(System.in);

    private static String accountName = "";
    private static int accountPin = 0;
    private static double accountBalance = 0.0;
    private static boolean isAccountCreated = false;

    private static Bank bank = new Bank();

    public static void main(String[] args) {
        while (true) {
            System.out.print("""
            
            ===============================
                 WELCOME TO THE BANK APP   
            ===============================
            1 -> Create Account
            2 -> Deposit Money
            3 -> Withdraw Money
            4 -> Check Balance
            5 -> Exit Application
            ===============================
            Enter your option:\s""");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    System.out.println("Thank you for using our Bank App. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account name: ");
        input.nextLine();
        accountName = input.nextLine();

        System.out.print("Enter your 4-digit PIN: ");
        accountPin = input.nextInt();

        accountBalance = 0.0;
        isAccountCreated = true;
        System.out.println("Account created successfully for " + accountName + "!");

        Account newAccount = bank.registerCustomer(accountName, accountPin);
        System.out.println("Your 10-digit NUBAN Account Number is: " + newAccount.getAccountNumber());
    }

    private static void deposit() {
        if (!isAccountCreated) {
            System.out.println("Error: Please create an account first!");
            return;
        }

        System.out.print("Enter amount to deposit: ₦");
        double amount = input.nextDouble();

        if (amount > 0) {
            accountBalance = accountBalance + amount;
            System.out.println("Successfully deposited ₦" + amount);
        } else {
            System.out.println("Invalid amount! You cannot deposit negative money.");
        }
    }

    private static void withdraw() {
        if (!isAccountCreated) {
            System.out.println("Error: Please create an account first!");
            return;
        }

        System.out.print("Enter your PIN: ");
        int enteredPin = input.nextInt();

        if (enteredPin != accountPin) {
            System.out.println("Wrong PIN! Transaction cancelled.");
            return;
        }

        System.out.print("Enter amount to withdraw: ₦");
        double amount = input.nextDouble();

        if (amount > accountBalance) {
            System.out.println("Insufficient funds! Your balance is lower than ₦" + amount);
        } else if (amount <= 0) {
            System.out.println("Invalid amount!");
        } else {
            accountBalance = accountBalance - amount;
            System.out.println("Successfully withdrew ₦" + amount);
        }
    }

    private static void checkBalance() {
        if (!isAccountCreated) {
            System.out.println("Error: Please create an account first!");
            return;
        }

        System.out.print("Enter your PIN: ");
        int enteredPin = input.nextInt();

        if (enteredPin != accountPin) {
            System.out.println("Wrong PIN! Cannot show balance.");
            return;
        }

        System.out.println("Hello " + accountName + ", your current balance is: ₦" + accountBalance);
    }
}
