/**
 * @author David Jefts
 * @date 9/12/18
 * @course CS420 Operating Systems
 * @function Practice using threads in Java.
 *      Using the java.lang.Thread class:
 *      Create 3 threads, have them sequentially print out every number from 0 to 4000
 *      The threads will run in whatever order is decided by the operating system
 *
 * @saving Make sure this file and the prob1.bat files are saved inside of the Prob1 folder
 * @runtime Double-Click on prob1.bat to run!
 */

public class SimpleThread1 {

    public static void main(String[] args) {
        ThreadCounter1 counterA = new ThreadCounter1("A");
        ThreadCounter1 counterB = new ThreadCounter1("B");
        ThreadCounter1 counterC = new ThreadCounter1("C");
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

class ThreadCounter1 extends Thread {
    private String name;

    public ThreadCounter1(String n) {
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
