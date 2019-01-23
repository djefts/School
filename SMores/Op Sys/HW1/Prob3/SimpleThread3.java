/**
 * @author davidjefts
 * @date 9/12/18
 * @course CS420 Operating Systems
 * @function Practice using threads in Java.
 *      Using the java.lang.Thread class:
 *      Create 3 threads, have them sequentially print out every number from 0 to 4000
 *      These threads will run one at a time
 *
 * @saving Make sure this file and the prob3.bat files are saved inside of the Prob3 folder, and
 *      the main folder containing all of the files is saved onto C:/Desktop/
 * @runtime Double-Click on prob3.bat to run!
 */

public class SimpleThread3 {

    public static void main(String[] args) {
        ThreadCounter3 counterA = new ThreadCounter3("A");
        ThreadCounter3 counterB = new ThreadCounter3("B");
        ThreadCounter3 counterC = new ThreadCounter3("C");
        Thread A = new Thread(counterA);
        Thread B = new Thread(counterB);
        Thread C = new Thread(counterC);

//        A.start();
//        while(A.isAlive()) {
//            Thread.yield();
//        }
//        B.start();
//        while(B.isAlive()) {
//            Thread.yield();
//        }
//        C.start();
//        while(true) {
//            if(!A.isAlive() && !B.isAlive() && !C.isAlive()) {
//                System.out.println("\n\nMain method is finished.");
//                break;
//            }
//

        A.start();
        B.start();
        C.start();
    }
}

class ThreadCounter3 extends Thread {
    private String name;

    public ThreadCounter3(String n) {
        super();
        name = n;
    }

    public void run() {
        final int max = 4000;
        System.out.println("Thread " + name + " now running.");
        for(int i = 1; i <= max; i++) {
            System.out.println(name + i);
            if(i%10 == 0) {
                Thread.yield();
            }
        }
        System.out.println("Finished running " + name + "\n");
    }
}
