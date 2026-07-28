package bankApp;

public class Account {

    private double balance;
    private int pin;
    private String firstName;
    private String lastName;
    private String accountNumber;

    public Account (int passWord,String firstName,String lastName, String accountNumber){
        this.pin = passWord;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void depositMoney(double amount){
        if(amount <= 0){
            return;
        }
        balance = balance + amount;
    }

    public double getBalance(int enterPin) {

        if (enterPin != this.pin) {

            throw new IllegalArgumentException("Incorrect Pin");

        }
        return balance;
    }
    public void withdrawal(double amount, int enterPin ){
        if(enterPin == this.pin && amount <= balance){
            balance = balance - amount;
        }
    }
}
