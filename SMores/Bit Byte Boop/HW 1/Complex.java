import java.text.DecimalFormat;

public class Complex {

    private double a; //real part
    private double b; //imaginary part

    public Complex() {
        a = b = 0;
    }

    public Complex(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public Complex add(Complex complex) {
        //(a + bi) + (c + di) = (a + c) + (b + d)i

        if(complex == null) {
            return null;
        }

        double c = complex.getRealPart();
        double d = complex.getImaginaryPart();
        double real = a + c;
        double imag = b + d;

        return new Complex(real, imag);
    }

    public Complex subtract(Complex complex) {
        //(a + bi) - (c + di) = (a - c) + (b - d)i

        if(complex == null) {
            return null;
        }
        double c = complex.getRealPart();
        double d = complex.getImaginaryPart();
        double real = a - c;
        double imag = b - d;

        return new Complex(real, imag);
    }

    public Complex multiply(Complex complex) {
        //(a + bi)*(c + di) = (ac - bd) + (bc + ad)i

        if(complex == null) {
            return null;
        }

        double c = complex.getRealPart();
        double d = complex.getImaginaryPart();
        double real = a * c - b * d;
        double imag = b * c + a * d;

        return new Complex(real, imag);
    }

    public Complex divide(Complex complex) throws ArithmeticException {
        //(a+bi)/(c+di)=(ac+bd)/(c^2 +d^2)+(bc-ad)i/(c^2 +d^2)

        if(complex == null) {
            return null;
        } else if(complex.getRealPart() == 0 && complex.getImaginaryPart() == 0) {
            System.out.println("You can't divide by 0!");
            throw new ArithmeticException();
        }


        double c = complex.getRealPart();
        double d = complex.getImaginaryPart();
        double real = (a * c + b * d) / (Math.pow(c, 2) + Math.pow(d, 2));
        double imag = (b * c - a * d) / (Math.pow(c, 2) + Math.pow(d, 2));

        //format real/imaginary parts to two decimal places
        DecimalFormat df = new DecimalFormat("#.000");
        real = Double.parseDouble(df.format(real));
        imag = Double.parseDouble(df.format(imag));

        return new Complex(real, imag);
    }

    public Complex abs() {
        //|𝑎+𝑏𝑖|=√(𝑎^2 +𝑏^2)

        double real = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        double imag = 0;

        DecimalFormat df = new DecimalFormat("#.000");
        real = Double.parseDouble(df.format(real));
        imag = Double.parseDouble(df.format(imag));
        return new Complex(real, imag);
    }

    public double getRealPart() {
        return this.a;
    }

    public double getImaginaryPart() {
        return this.b;
    }

    @Override
    public String toString() {
        String B;
        if(b > 0) {
            B = " + " + b + "i";
        } else if(b == 0) {
            return a + "";
        } else {
            B = " - " + Math.abs(b) + "i";
        }
        return "(" + a + B + ")";
    }
}
