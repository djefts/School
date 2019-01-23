import java.util.Scanner;

/**
 * @author dgb
 * Tester class for OurLinkedStack
 */
public class OurLinkedStackTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OurLinkedStack<String> stringStack = new OurLinkedStack<String>();
        getDataFromUser(sc, stringStack);
        System.out.println(stringStack);
        dumpStack(stringStack);  //Note: This method DESTROYS the stack
    }//end main

    public static void getDataFromUser(Scanner sc,
                                       OurLinkedStack<String> stringStack) {
        System.out.print("Enter any string. Just press enter to finish data entry. ");
        String str = sc.nextLine();
        while(str.length() != 0) {
            stringStack.push(str);
            System.out.print("Enter any string. Just press enter to finish data entry. ");
            str = sc.nextLine();
        }//end while        
    }

    public static void dumpStack(OurLinkedStack<String> stringStack) {
        while(!stringStack.isEmpty()) {
            System.out.println("Stack has " + stringStack.size() + " object(s).");
            System.out.println("Top of stack: " + stringStack.peek());
            System.out.println(stringStack.pop() + " removed from top of stack.");
        }
    }
}//end class
