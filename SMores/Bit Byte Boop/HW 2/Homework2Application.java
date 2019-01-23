import java.util.Scanner;

/**
 * @author David Jefts
 */
public class Homework2Application {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        OurLinkedStack<Character> charStack = new OurLinkedStack<Character>();
        getCharactersFromUser(sc, charStack);
        if(charStack.size() > 0) {
            displayStack(charStack);
            int n = getNumberFromUser(sc, "n");
            int k = getNumberFromUser(sc, "k");
            roll(charStack, n, k);
            System.out.println("\nThe rolled stack: ");
            displayStack(charStack);
        } else {
            System.out.println("You did not enter anything.");
        }

        sc.close();
    }//end main

    public static void getCharactersFromUser(Scanner sc, OurLinkedStack<Character> charStack) {
        System.out.println("Please enter any string");
        String str = sc.nextLine();
        for(int i = 0; i<str.length(); i++) {
            charStack.push(str.charAt(i));
        }
    }

    public static void displayStack(OurLinkedStack<Character> charStack) {
        //charStack toString is set up to do this, and it looks all pretty and stuffs
        System.out.println(charStack);
    }

    public static int getNumberFromUser(Scanner sc, String which) {
        //This method gets either the n or the k needed for the roll method
        int value;
        System.out.print("Enter a non-negative value for " + which + " ");
        String ans = sc.nextLine();
        if(ans.length() == 0)
            return 0;
        value = Integer.parseInt(ans);
        return value;
    }

    public static void roll(OurLinkedStack<Character> stack, int n, int k) throws Exception {
        //Insert code to implement roll as described in the text.
        //You may need a temporary stack and a queue.
        OurLinkedStack<Character> tempS = new OurLinkedStack<>();
        OurLinkedQueue<Character> tempQ = new OurLinkedQueue<>();
        //pop n elements from stack to temporary queue
        for(int i = 0; i<n; i++) {
            tempQ.add(stack.pop());
        }
        //rotate temporary queue k times
        for(int i = 0; i<k; i++) {
            tempQ.add(tempQ.remove());
        }
        //pop queue and add to temporary stack
        int size = tempQ.size();
        for(int i = 0; i<size; i++) {
            tempS.push(tempQ.remove());
        }
        //pop off temporary stack and add to the real stack
        for(int i = 0; i<size; i++) {
            stack.push(tempS.pop());
        }
    }
}//end class
