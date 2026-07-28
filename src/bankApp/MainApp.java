package bankApp;

import java.util.Scanner;

public class MainApp {
    private static Scanner input = new Scanner(System.in);
    private static Bank bank = new Bank("GTBank");

    public static void main(String[] args) {
        while (true) {
            System.out.print("""
            
            ===========================================
                 WELCOME TO THE SEUNFUNMI'S BANK APP
            ===========================================
            1 -> Create Account
            2 -> Deposit Money
            3 -> Withdraw Money
            4 -> Check Balance
            5 -> Transfer
            6 -> Exit Application
            ===========================================
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
                    transfer();
                    break;
                case 6:
                    System.out.println("Thank you for using our Bank App. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter first name: ");
        input.nextLine();
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter your 4-digit PIN: ");
        int accountPin = input.nextInt();

        Account newAccount = bank.registerCustomer(firstName, lastName, accountPin);
        System.out.println("Account created successfully for " + firstName + " " + lastName + "!");
        System.out.println("Your 10-digit NUBAN Account Number is: " + newAccount.getAccountNumber());
    }

    private static void deposit() {
        System.out.print("Enter 10-digit Account Number: ");
        String accountNumber = input.next();

        System.out.print("Enter amount to deposit: ₦");
        double amount = input.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount! You cannot deposit negative money.");
            return;
        }

        Account account = bank.findAccount(accountNumber);
        if (account != null) {
            bank.deposit(accountNumber, amount);
            System.out.println("Successfully deposited ₦" + amount + " into account " + accountNumber);
        } else {
            System.out.println("Error: Account number does not exist!");
        }
    }

    private static void withdraw() {
        System.out.print("Enter 10-digit Account Number: ");
        String accountNumber = input.next();

        Account account = bank.findAccount(accountNumber);
        if (account == null) {
            System.out.println("Error: Account number does not exist!");
            return;
        }

        System.out.print("Enter your PIN: ");
        int enteredPin = input.nextInt();

        System.out.print("Enter amount to withdraw: ₦");
        double amount = input.nextDouble();

        bank.withdraw(accountNumber, amount, enteredPin);
        System.out.println("Withdrawal operation completed.");
    }

    private static void checkBalance() {
        System.out.print("Enter 10-digit Account Number: ");
        String accountNumber = input.next();

        System.out.print("Enter your PIN: ");
        int enteredPin = input.nextInt();

        try {
            double currentBalance = bank.checkBalance(accountNumber, enteredPin);
            System.out.println("Current balance for account " + accountNumber + " is: ₦" + currentBalance);
        } catch (IllegalArgumentException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }

    private static void transfer() {
        System.out.print("Enter Sender's Account Number: ");
        String senderAccountNumber = input.next();

        System.out.print("Enter your PIN: ");
        int enterPin = input.nextInt();

        System.out.print("Enter Receiver's Account Number: ");
        String receiverAccountNumber = input.next();

        System.out.print("Enter amount: ₦");
        double amount = input.nextDouble();

        bank.transfer(senderAccountNumber, receiverAccountNumber, amount, enterPin);
        System.out.println("Transfer processing completed.");
    }
}
