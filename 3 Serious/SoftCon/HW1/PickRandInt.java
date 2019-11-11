import java.util.Scanner;

public class PickRandInt {
    
    public static void main(String[] args) {
        int[] randNums = new int[100];
        for(int i = 0; i<randNums.length; i++) {
            randNums[i] = (int)(Math.random()*100);
        }
    
        Scanner in = new Scanner(System.in);
        System.out.print("Enter array index:\t");
        int input = in.nextInt();
        try {
            System.out.println(randNums[input]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Out of Bounds- " + e.getMessage());
            throw e;
        }
    }
}
