
public class Account {

            private double balance;
            private int pin;

            public Account (int passWord){
            this.pin = passWord;
    }


    public void depositMoney(double amount){
        if(amount <= 0){
            return;
        }
            balance = balance + amount;
        }
        public double getBalance(){
            return balance;
        }

        public void withdrawal(double amount, int enterPin ){
            if(enterPin == this.pin && amount <= balance){
                balance = balance - amount;
            }

        }







}

