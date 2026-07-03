import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    public void testNewAccountShouldHaveZeroBalance(){

        Account account = new Account(1234);
        assertEquals(0, account.getBalance());

    }
    @Test
    public void testThatAccountDeposit(){
        Account account = new Account(1234);
        account.depositMoney(40000.00);

        assertEquals(40000.00, account.getBalance());

    }
    @Test

    public void testThatAccountTakesMultiplyTransaction(){
        Account account = new Account(1234);
        account.depositMoney(30000.00);
        account.depositMoney(60000.00);

        assertEquals(90000.00,account.getBalance());
    }

    @Test
    public void testThatAccountCanMakeWithdrawal(){
        Account account = new Account(1234);
        account.depositMoney(90000.000);
        account.withdrawal(30000.00, 1234);

        assertEquals(60000.00,account.getBalance());

    }
    @Test
    public void testThatAccountCantTakeNegativeAmount(){
        Account account = new Account(1234);
        account.depositMoney(-30000.00);

        assertEquals(0, account.getBalance());

    }
    @Test
    public void testThatAccountCanWithdrawalAboveBalance(){
        Account account = new Account(1234);
        account.depositMoney(10000.00);
        account.withdrawal(100000.00, 1234);

        assertEquals(10000.00, account.getBalance());
    }

    @Test
    public void testThatAccountCanDepositZero(){
            Account account = new Account(1234);
            account.depositMoney(0.00);

            assertEquals(0,account.getBalance());
    }

    @Test
    public void testThatAccountWithdrawlShouldFailDueToWrongPassword(){
        Account account = new Account(1234);
        account.depositMoney(12000.00);
        account.withdrawal(8000.00, 2356);

        assertEquals(12000.00,account.getBalance());
    }





}