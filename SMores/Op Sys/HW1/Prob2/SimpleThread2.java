/**
 * @author davidjefts
 * @date 9/12/18
 * @course CS420 Operating Systems
 * @function Practice using threads in Java.
 *      Using the java.lang.Runnable class:
 *      Create 3 threads, have them sequentially print out every number from 0 to 4000
 *      The threads will run in whatever order is decided by the operating system
 *
 * @saving Make sure this file and the prob2.bat files are saved inside of the Prob2 folder, and
 *      the main folder containing all of the files is saved onto C:/Desktop/
 * @runtime Double-Click on prob2.bat to run!
 */

public class SimpleThread2 {

    public static void main(String[] args) {
        ThreadCounter2 counterA = new ThreadCounter2("A");
        ThreadCounter2 counterB = new ThreadCounter2("B");
        ThreadCounter2 counterC = new ThreadCounter2("C");
        Thread A = new Thread(counterA);
        Thread B = new Thread(counterB);
        Thread C = new Thread(counterC);
        A.start();
        B.start();
        C.start();

        while(true) {
            if(!A.isAlive() && !B.isAlive() && !C.isAlive()) {
                System.out.println("\n\nMain method is finished.");
                break;
            }
        }
    }
}

class ThreadCounter2 implements Runnable {
    private String name;

    public ThreadCounter2(String n) {
        super();
        name = n;
    }

    public void run() {
        final int max = 4000;
        System.out.println("Thread " + name + " now running.");
        for(int i = 1; i <= max; i++) {
            System.out.println(name + i);
        }
        System.out.println("\nFinished running " + name);
    }
}
