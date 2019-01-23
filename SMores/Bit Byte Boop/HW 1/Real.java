import java.text.DecimalFormat;

public class Real extends Complex {
    public Real() {
        super();
    }

    public Real(double a) {
        super(a, 0);
    }

    @Override
    public String toString() {
        return "" + this.getRealPart();
    }
}
