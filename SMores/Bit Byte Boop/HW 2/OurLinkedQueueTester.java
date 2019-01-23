import java.util.Scanner;

/**
 * @author dgb
 * Tester class for OurLinkedQueue
 */
public class OurLinkedQueueTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OurLinkedQueue<String> stringQueue = new OurLinkedQueue<String>();
        getDataFromUser(sc, stringQueue);
        dumpQueue(stringQueue); //this method DESTROYS the queue       
    }//end main

    public static void getDataFromUser(Scanner sc,
                                       OurLinkedQueue<String> stringQueue) {
        System.out.print("Enter any string. Just press enter to finish "
                + " data entry. ");
        String str = sc.nextLine();
        while(str.length() != 0) {
            stringQueue.add(str);
            System.out.print("Enter any string. Just press enter to finish "
                    + " data entry. ");
            str = sc.nextLine();
        }//end while
    }

    public static void dumpQueue(OurLinkedQueue<String> stringQueue) {
        while(!stringQueue.isEmpty()) {
            System.out.println("Queue has " + stringQueue.size() + " object(s).");
            System.out.println("Front of queue: " + stringQueue.peek());
            System.out.println(stringQueue.remove()
                    + " removed from front of queue.");
        }
    }

}//end class
