package bankApp;

import java.util.ArrayList;

public class Bank {
    private String name;
    private final String bankCode = "011";
    private ArrayList<Account> accounts = new ArrayList<>();

    public Bank(String name) {
        this.name = name;
    }

    public Account registerCustomer(String firstName, String lastName, int pin) {
        String newAccountNumber = generateAccountNumber();
        Account account = new Account(pin, firstName, lastName, newAccountNumber);
        accounts.add(account);
        return account;
    }

    private String generateAccountNumber() {
        int enterId = accounts.size() + 1;
        String serialNumber = "" + enterId;

        while (serialNumber.length() < 9) {
            serialNumber = "0" + serialNumber;
        }

        String generate = bankCode + serialNumber;
        int[] combination = {3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3};
        int sum = 0;

        for (int count = 0; count < generate.length(); count++) {
            int digit = generate.charAt(count) - '0';
            sum += digit * combination[count];
        }

        int module = sum % 10;
        int subtract = 10 - module;
        if (subtract == 10) {
            subtract = 0;
        }

        return serialNumber + subtract;
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void deposit(String accountNumber, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount");
            return;
        }
        Account targetAccount = findAccount(accountNumber);
        if (targetAccount != null) {
            targetAccount.depositMoney(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    public void withdraw(String accountNumber, double amount, int pin) {
        Account targetAccount = findAccount(accountNumber);
        if (targetAccount != null) {
            targetAccount.withdrawal(amount, pin);
        } else {
            System.out.println("Account not found!");
        }
    }

    public void transfer(String senderAccountNumber, String receiverAccountNumber, double amount, int pin) {
        Account sender = findAccount(senderAccountNumber);
        Account receiver = findAccount(receiverAccountNumber);

        if (sender == null) {
            System.out.println("Sender account not found!");
            return;
        }
        if (receiver == null) {
            System.out.println("Receiver account not found!");
            return;
        }

        sender.withdrawal(amount, pin);
        receiver.depositMoney(amount);
    }

    public double checkBalance(String accountNumber, int pin) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found");
            return 0.0;
        }
        return account.getBalance(pin);
    }
}
