/**
 * @author dgb
 */
public class Savings extends Account {
    private double interestRate;

    public Savings(int acctNum) throws Exception {
        super(acctNum);
        interestRate = 0.1;
    }

    public double getRate() {
        return interestRate;
    }

    public void setRate(double rate) {
        if(rate < 0.1)
            rate = 0.1;
        interestRate = rate;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            double interest = interestRate * getBalance();
            amount += interest;
            super.deposit(amount);
        }
    }

    public String toString() {
        return "Savings: " + super.toString();
    }
}
