import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author dgb
 * This class is designed to act as a starter class for the Arithmetic class
 * of homework 1.
 * If you are having trouble with that class, start here.
 * It is meant to be a minimal class and you have to fill it in a lot more.
 * I deliberately kept error-checking to a minimum.
 * <p>
 * Also, this assumes that your Complex and Real classes are working properly.
 */
public class Arithmetic {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Complex comp1 = getNumberFromUser("first");
        Complex comp2 = getNumberFromUser("second");
        char operation = getOperationFromUser();
        performOperation(comp1, comp2, operation);
        scanner.close();
    }//end main

    public static Complex getNumberFromUser(String whichNumber) {
        //initial Complex number
        Complex number = null;

        //take in user input
        System.out.println("Enter the " + whichNumber + " number in the form 'R [value]' or 'C [value1] [value2]'");
        String input;
        try {
            input = scanner.nextLine();
        } catch(InputMismatchException e) {
            System.out.println(e);
            return getNumberFromUser(whichNumber);
        }
        String[] inputs = input.split(" ");  //break up string

        //parse user input
        try {
            if(inputs.length == 2 && inputs[0].equalsIgnoreCase("R")) {
                //user entered a Real number
                double real = Double.parseDouble(inputs[1]);
                return new Real(real);
            } else if(inputs.length == 3 && inputs[0].equalsIgnoreCase("C")) {
                //user entered a Complex number
                double real = Double.parseDouble(inputs[1]);
                double imag = Double.parseDouble(inputs[2]);
                return new Complex(real, imag);
            } else {
                System.out.println("\n\nPlease enter either an 'R' or a 'C' followed by the correct amount of numerical values.");
                return getNumberFromUser(whichNumber);
            }
        } catch (NumberFormatException word) {
            System.out.println("\n\nPlease enter double or integer values for [values]");
            return getNumberFromUser(whichNumber);
        }
    }

    public static char getOperationFromUser() {
        System.out.println("Please make a choice from the selection below");
        System.out.println("To add enter +");
        System.out.println("To subtract enter -");
        System.out.println("To multiply enter *");
        System.out.println("To divide enter /");
        System.out.println("To find the absolute value enter |");
        String choice = scanner.nextLine();
        if(choice.length() == 0) {  //They just pressed enter
            scanner.close();
            return '?'; //return junk
        }
        return choice.charAt(0);
    }

    public static void performOperation(Complex comp1, Complex comp2, char operation) {
        Complex comp = null;

        switch(operation) {
            case '+':
                comp = comp1.add(comp2);
                System.out.println(comp1 + " + " + comp2 + " = " + comp);
                break;
            case '-':
                comp = comp1.subtract(comp2);
                System.out.println(comp1 + " - " + comp2 + " = " + comp);
                break;
            case '*':
                comp = comp1.multiply(comp2);
                System.out.println(comp1 + " * " + comp2 + " = " + comp);
                break;
            case '/':
                comp = comp1.divide(comp2);
                System.out.println(comp1 + " / " + comp2 + " = " + comp);
                break;
            case '|':
                comp = comp1.abs();
                System.out.println("|" + comp1 + "|" + " = " + comp);
                comp = comp2.abs();
                System.out.println("|" + comp2 + "|" + " = " + comp);
                break;
            default:
                System.out.println("Invalid operation");
                getOperationFromUser();
        }
    }
}
