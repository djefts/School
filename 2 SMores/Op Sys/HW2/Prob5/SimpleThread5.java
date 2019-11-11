/**
 * @author David Jefts
 * @date 9/17/18
 * @course CS420 Operating Systems
 * @function Practice using threads in Java.
 * Using the java.lang.Thread class:
 * Create 3 threads
 * Threads 1 and 2 should sleep for 500ms and then print out time since program start
 * Thread 3 should do the same but sleep 1300ms
 * Once finished print out the total runtime
 * Change the priorities of the threads and see what happens
 * @saving Make sure this file and the prob1.bat files are saved inside of the Prob1 folder
 * @runtime Double-Click on prob1.bat to run!
 */
public class SimpleThread5 {
    static double time = System.currentTimeMillis();

    public static void main(String[] args) {
        Threads2 thread1 = new Threads2("A1", true, time);
        Threads2 thread2 = new Threads2("A2", true, time);
        Threads2 thread3 = new Threads2("B1", false, time);
        Thread A = new Thread(thread1);
        Thread B = new Thread(thread2);
        Thread C = new Thread(thread3);

        A.start();
        B.start();
        C.start();
        A.setPriority(Thread.MAX_PRIORITY);
        B.setPriority(Thread.MIN_PRIORITY);
        C.setPriority(Thread.NORM_PRIORITY);
        System.out.println("Main: " + (System.currentTimeMillis() - time) + " milliseconds.");
    }
}

class Threads2 extends Thread {
    private String name;
    boolean thread;
    double start;

    public Threads2(String a, boolean x, double start) {
        super();
        name = a;
        thread = x;
        this.start = start;
    }

    public void run() {
        try {
            if(thread) {
                Thread.sleep(500);
            } else {
                Thread.sleep(1300);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
            System.exit(1);
        }
        double totTime = System.currentTimeMillis() - start;
        System.out.println(name + ": " + totTime + " milliseconds.  \t"  + getPriority());
    }
}
