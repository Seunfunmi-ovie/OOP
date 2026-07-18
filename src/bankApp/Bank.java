package bankApp;
import java.util.ArrayList;

public class Bank {
    private String name;
    private final String bankCode = "011";
    private ArrayList<Account> accounts = new ArrayList<>();

    public Account registerCustomer(String name, int pin){
        String newAccountNumber = generateAccountNumber();
        Account account = new Account(pin,"","",newAccountNumber);
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
        int [] combination = {3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3};
        int sum = 0;

        for(int count = 0; count < generate.length(); count++){
            int digit = generate.charAt(count) - '0';
            sum += digit * combination[count];
        }

        int module = sum % 10;
        int subtract = 10 - module;
        if(subtract == 10){
            subtract = 0;
        }
        return serialNumber + subtract;
    }

    public Account findAccount(int accountNumber) {
        int targetIndex = accountNumber - 1;
        if (targetIndex >= 0 && targetIndex < accounts.size()) {
            return accounts.get(targetIndex);
        }
        return new Account(1234,"","","");
    }

    public void deposit(int accountNumber, double amount){
        Account targetAccount = findAccount(accountNumber);
        targetAccount.depositMoney(amount);
    }

    public void withdraw(int accountNumber, double amount, int pin) {
        Account targetAccount = findAccount(accountNumber);
        targetAccount.withdrawal(amount, pin);
    }

    public void transfer(int senderAccountNumber, int receiverAccountNumber, double amount, int pin) {
        Account sender = findAccount(senderAccountNumber);
        Account receiver = findAccount(receiverAccountNumber);

        double initialBalance = sender.getBalance();
        sender.withdrawal(amount, pin);
        if (sender.getBalance() < initialBalance) {
            receiver.depositMoney(amount);
        }
    }
}
