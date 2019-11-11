


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;


public class ComplexTest {
    private ArrayList<Complex> numbers;

    public static void main(String[] args) {
        ComplexTest testies = new ComplexTest();
        testies.test();

    }

    @Test
    public void test() {
        //Complex nullType = new Complex();
        //nullType = null;
        numbers = new ArrayList<>();
        Complex c0 = new Complex();
        Real r0 = new Real();
        Complex c1 = new Complex(4, 2);
        Complex c2 = new Complex(2, 4);
        Real r1 = new Real(1);
        Real r2 = new Real(2);


        System.out.println(c0.add(c0));
        System.out.println(c0);
        Assertions.assertSame(c0.add(c0), 0.0);
        Assertions.assertSame(c0.subtract(c0), 0.0);
        Assertions.assertSame(c0.multiply(c0), 0.0);
        try {
            c0.divide(c0);
        } catch (ArithmeticException e) {
            System.out.println(true);
        }

        Assertions.assertSame(r2.add(c0), c0);
        Assertions.assertSame(r2.subtract(c0), c0);
        Assertions.assertSame(r2.multiply(c0), c0);
        try {
            r2.divide(c0);
        } catch (ArithmeticException e) {
            System.out.println(true);
        }
    }

    public Complex sumOfNumbers() {
        Complex sum = numbers.get(0);
        for(Complex number : numbers) {
            sum = sum.add(number);
        }
        return sum;
    }

    public Complex differenceOfNumbers() {
        Complex difference = numbers.get(0);
        for(int i = 1; i < numbers.size(); i++) {
            difference = difference.subtract(numbers.get(i));
        }
        return difference;
    }

    public Complex productOfNumbers() {
        Complex product = numbers.get(0);
        for(int i = 1; i < numbers.size(); i++) {
            product = product.multiply(numbers.get(i));
        }
        return product;
    }

    public Complex quotientOfNumbers() {
        Complex quotient = numbers.get(0);
        for(int i = 1; i < numbers.size(); i++) {
            try {
                quotient = quotient.divide(numbers.get(i));
            } catch (ArithmeticException e) {
                System.out.println("\nYou can't divide by 0 bro!");
                return null;
            }
        }
        return quotient;
    }

    public Complex absoluteValueOfNumbers() {
        return numbers.get(0).abs();
    }

    public String toString() {
        System.out.println("\n\n\nSum of numbers = " + sumOfNumbers());
        System.out.println("\nDifference of numbers = " + differenceOfNumbers());
        System.out.println("\nProduct of numbers = " + productOfNumbers());
        System.out.println("\nQuotient of numbers = " + quotientOfNumbers());
        System.out.println("\nAbsolute value of " + numbers.get(0) + " = " + absoluteValueOfNumbers());
        return "";
    }
}
