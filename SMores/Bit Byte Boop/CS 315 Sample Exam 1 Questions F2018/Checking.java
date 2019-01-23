/**
 * @author dgb
 */
public class Checking extends Account {
    public static final double MAX_WITHDRAWAL = 400;
    public static final double START_BALANCE = 100;

    public Checking(int acctNum, double amount) throws Exception {
        super(acctNum);
        if(amount < START_BALANCE)
            throw new Exception("Can't open a checking without at least $"
                    + START_BALANCE);
        deposit(amount);
    }

    public double withdraw(double amount) {
        if(amount > MAX_WITHDRAWAL) {
            amount = MAX_WITHDRAWAL;
        }

        return super.withdraw(amount);
    }

    public String toString() {
        return "Checking: " + super.toString();
    }
}
