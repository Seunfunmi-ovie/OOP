package bankApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    @Test
    public void testThatBankCanRegisterCustomer() {
        Bank myBank = new Bank("GTBank");
        Account newAccount = myBank.registerCustomer("Seunfunmi", "Donwa", 2356);
        assertNotNull(newAccount);
    }

    @Test
    public void testThatBankCanDepositMoneyIntoAccount() {
        Bank myBank = new Bank("GTBank");
        Account account = myBank.registerCustomer("Samuel", "Donwa", 2358);
        String accountNumber = account.getAccountNumber();

        myBank.deposit(accountNumber, 1245.00);

        Account foundAccount = myBank.findAccount(accountNumber);
        assertEquals(1245.00, foundAccount.getBalance(2358));
    }

    @Test
    public void testThatAccountsGetAccountNumberIsCreated() {
        Bank myBank = new Bank("GTBank");

        Account first = myBank.registerCustomer("First", "Person", 1111);
        assertEquals(first, myBank.findAccount(first.getAccountNumber()));

        Account second = myBank.registerCustomer("Second", "Person", 2222);
        assertEquals(second, myBank.findAccount(second.getAccountNumber()));
    }

    @Test
    public void testThatBankCanFindTheRegisteredCustomers() {
        Bank myBank = new Bank("GTBank");
        Account account = myBank.registerCustomer("Ola", "Olaomo", 4444);

        Account foundAccount = myBank.findAccount(account.getAccountNumber());
        assertNotNull(foundAccount);
    }

    @Test
    public void testThatBankCanWithdraw() {
        Bank myBank = new Bank("GTBank");
        Account account = myBank.registerCustomer("Samuel", "Donwa", 2358);
        String accountNumber = account.getAccountNumber();

        myBank.deposit(accountNumber, 5000.00);
        myBank.withdraw(accountNumber, 2000.00, 2358);

        Account foundAccount = myBank.findAccount(accountNumber);
        assertEquals(3000.00, foundAccount.getBalance(2358));
    }

    @Test
    public void testThatWithdrawalFailsForInvalidPin() {
        Bank myBank = new Bank("GTBank");
        Account account = myBank.registerCustomer("Samuel", "Donwa", 2358);
        String accountNumber = account.getAccountNumber();

        myBank.deposit(accountNumber, 5000.00);
        myBank.withdraw(accountNumber, 2000.00, 9999);

        Account foundAccount = myBank.findAccount(accountNumber);
        assertEquals(5000.00, foundAccount.getBalance(2358));
    }

    @Test
    public void testThatBankCanTransferMoneyBetweenAccounts() {
        Bank myBank = new Bank("GTBank");
        Account first = myBank.registerCustomer("Samuel", "Donwa", 1111);
        Account second = myBank.registerCustomer("funmi", "Donwa", 2222);

        String senderNum = first.getAccountNumber();
        String receiverNum = second.getAccountNumber();

        myBank.deposit(senderNum, 10000.00);
        myBank.transfer(senderNum, receiverNum, 4000.00, 1111);

        Account sender = myBank.findAccount(senderNum);
        Account receiver = myBank.findAccount(receiverNum);
        assertEquals(6000.00, sender.getBalance(1111));
        assertEquals(4000.00, receiver.getBalance(2222));
    }

    @Test
    public void testThatBankTransferFailsWithWrongPin() {
        Bank myBank = new Bank("GTBank");
        Account first = myBank.registerCustomer("Account", "One", 1111);
        Account second = myBank.registerCustomer("Account", "Two", 2222);

        String senderNum = first.getAccountNumber();
        String receiverNum = second.getAccountNumber();

        myBank.deposit(senderNum, 10000.00);
        myBank.transfer(senderNum, receiverNum, 4000.00, 9999);

        Account sender = myBank.findAccount(senderNum);
        Account receiver = myBank.findAccount(receiverNum);
        assertEquals(10000.00, sender.getBalance(1111));
        assertEquals(0.00, receiver.getBalance(2222));
    }
}
