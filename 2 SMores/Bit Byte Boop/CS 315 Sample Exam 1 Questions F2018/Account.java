/**
 * @author dgb
 */
public class Account {
    private int acctNum;
    private double balance;

    public Account(int acctNum) throws Exception {
        if(acctNum <= 0) throw new Exception("Invalid Account Number");
        this.acctNum = acctNum;
        balance = 0;
    }

    public int getAccountNumber() {
        return acctNum;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if(amount > 0)
            balance += amount;
    }

    public double withdraw(double amount) {
        if(amount <= balance) {
            balance -= amount;
            return amount;
        }
        return 0;
    }

    public String toString() {
        return "Acct #" + acctNum + " Balance: $" + balance;
    }
}
