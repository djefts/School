import java.util.InputMismatchException;
import java.util.Scanner;

public class InputSum {
    
    public static void main(String[] args) {
        
        InputSum inputSum = new InputSum();
        try(Scanner in = new Scanner(System.in)) {
            int num1 = inputSum.inputOne(in);
            int num2 = inputSum.inputTwo(in);
            int sum = num1 + num2;
            String output = String.format("Sum: %d + %d = %d", num1, num2, sum);
            System.out.println(output);
        } catch (Exception e) {
            System.out.println("Failed all the way up.\n");
            throw e;
        }
    }
    
    public int inputOne(Scanner in) {
        int num;
        try {
            System.out.print("Enter an integer:\t");
            String input = in.next();
            num = Integer.parseInt(input);
        } catch (Exception e) {
            /*System.out.println(e);*/
            /*in.nextInt();*/
            System.out.println("Bad input, try again.\n");
            return this.inputOne(in);
        }
        return num;
    }
    
    public int inputTwo(Scanner in) {
        int num;
        try {
            System.out.print("Enter a second integer:\t");
            String input = in.next();
            num = Integer.parseInt(input);
        } catch (Exception e) {
            /*System.out.println(e);*/
            /*in.next();*/
            System.out.println("Bad input, try again.\n");
            return inputTwo(in);
        }
        
        return num;
    }
}
