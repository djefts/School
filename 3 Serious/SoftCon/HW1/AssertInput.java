import java.util.Scanner;

public class AssertInput {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a number between 1 and 10:\t");
        int input = in.nextInt();
        assert (input > 0) && (input < 11) : "\"The entered number is out of range\"";
    }
}
