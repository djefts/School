/**
 * @author David Jefts
 * @date 10/8/18
 * @course CS420 Operating Systems
 * @saving Make sure this file and the prob1.bat files are saved inside of the Prob1 folder
 * @runtime Double-Click on prob1.bat to run!
 */
public class SynchronizedRace {
    public static void main(String[] args) {
        Resource3 resource = new Resource3();
        Increment3 thread1 = new Increment3(resource);
        Decrement3 thread2 = new Decrement3(resource);
        Thread A = new Thread(thread1);
        Thread B = new Thread(thread2);
        
        A.start();
        B.start();
    }
}

/**
 * this class will increment whatever value is in Resource.value
 */
class Increment3 extends Thread {
    Resource3 resource;
    
    public Increment3(Resource3 res) {
        resource = res;
    }
    
    public void run() {
        //get value, sleep, increment value
        int value;
        for(; ; ) {
            value = resource.getValue() + 1;
            System.out.println("\tIncrementing " + value);
            int sleep = 10 + (int) (Math.random() * 190);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted.");
            }
            //set value after sleeping
            resource.setValue(value);
        }
    }
}

/**
 * this class will decrement whatever value is in Resource.value
 */
class Decrement3 extends Thread {
    Resource3 resource;
    
    public Decrement3(Resource3 res) {
        resource = res;
    }
    
    public void run() {
        //get value, decrement value, sleep
        int value;
        for(; ; ) {
            value = resource.getValue() - 1;
            System.out.println("\tDecrementing " + value);
            //set value then sleep
            resource.setValue(value);
            int sleep = 10 + (int) (Math.random() * 190);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted.");
            }
        }
    }
}