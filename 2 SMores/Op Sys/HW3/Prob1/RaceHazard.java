/**
 * @author David Jefts
 * @date 10/8/18
 * @course CS420 Operating Systems
 * @saving Make sure this file and the prob1.bat files are saved inside of the Prob1 folder
 * @runtime Double-Click on prob1.bat to run!
 */
public class RaceHazard {
    public static void main(String[] args) {
        Resource1 resource = new Resource1();
        Increment thread1 = new Increment(resource);
        Decrement thread2 = new Decrement(resource);
        Thread A = new Thread(thread1);
        Thread B = new Thread(thread2);
        
        A.start();
        B.start();
    }
}

/**
 * this class will increment whatever value is in Resource.value
 */
class Increment extends Thread {
    Resource1 resource;
    
    public Increment(Resource1 res) {
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
class Decrement extends Thread {
    Resource1 resource;
    
    public Decrement(Resource1 res) {
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