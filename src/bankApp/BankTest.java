package bankApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    @Test
    public void testThatBankCanRegisterCustomer() {
        Bank myBank = new Bank();
        Account newAccount = myBank.registerCustomer("Seunfunmi Donwa", 2356);
        assertNotNull(newAccount);

    }

    @Test
    public void testThatBankCanDepositMoneyIntoAccount() {
        Bank myBank = new Bank();
        myBank.registerCustomer("Samuel Donwa", 2358);

        myBank.deposit(1, 1245.00);

        Account account = myBank.findAccount(1);
        assertEquals(1245.00, account.getBalance());
    }

    @Test
    public void testThatAccountsGetAccountNumberIsCreated() {
        Bank myBank = new Bank();

        Account first = myBank.registerCustomer("First Person", 1111);
        assertEquals(first, myBank.findAccount(1));

        Account second = myBank.registerCustomer("Second Person", 2222);
        assertEquals(second, myBank.findAccount(2));
    }

    @Test
    public void testThatBankCanFindTheRegisteredCustomers() {
        Bank myBank = new Bank();
        myBank.registerCustomer("Ola", 4444);

        Account foundAccount = myBank.findAccount(1);
        assertNotNull(foundAccount);
    }

    @Test
    public void testThatBankCanWithdraw() {
        Bank myBank = new Bank();
        myBank.registerCustomer("Samuel Donwa", 2358);
        myBank.deposit(1, 5000.00);

        myBank.withdraw(1, 2000.00, 2358);

        Account account = myBank.findAccount(1);
        assertEquals(3000.00, account.getBalance());
    }

    @Test
    public void testThatWithdrawalFailsForInvalidPin() {
        Bank myBank = new Bank();
        myBank.registerCustomer("Samuel Donwa", 2358);
        myBank.deposit(1, 5000.00);

        myBank.withdraw(1, 2000.00, 9999);

        Account account = myBank.findAccount(1);
        assertEquals(5000.00, account.getBalance());
    }

    @Test
    public void testThatBankCanTransferMoneyBetweenAccounts() {
        Bank myBank = new Bank();
        myBank.registerCustomer("Samuel's Account", 1111);
        myBank.registerCustomer("funmi's Account", 2222);
        myBank.deposit(1, 10000.00);

        myBank.transfer(1, 2, 4000.00, 1111);

        Account sender = myBank.findAccount(1);
        Account receiver = myBank.findAccount(2);
        assertEquals(6000.00, sender.getBalance());
        assertEquals(4000.00, receiver.getBalance());
    }

    @Test
    public void testThatBankTransferFailsWithWrongPin() {
        Bank myBank = new Bank();
        myBank.registerCustomer("Account 1", 1111);
        myBank.registerCustomer("Account 2", 2222);
        myBank.deposit(1, 10000.00);

        myBank.transfer(1, 2, 4000.00, 9999);

        Account sender = myBank.findAccount(1);
        Account receiver = myBank.findAccount(2);
        assertEquals(10000.00, sender.getBalance());
        assertEquals(0, receiver.getBalance());
    }
}
