/**
 * @author David Jefts
 * @date 10/8/18
 * @course CS420 Operating Systems
 * @saving Make sure this file and the prob1.bat files are saved inside of the Prob1 folder
 * @runtime Double-Click on prob1.bat to run!
 */
public class SyncRace {
    public static void main(String[] args) {
        ResourceSync resource = new ResourceSync();
        Increment4 thread1 = new Increment4(resource);
        Decrement4 thread2 = new Decrement4(resource);
        Thread A = new Thread(thread1);
        Thread B = new Thread(thread2);
        
        A.start();
        B.start();
    }
}

/**
 * this class will increment whatever value is in Resource.value
 */
class Increment4 extends Thread {
    ResourceSync resource;
    
    public Increment4(ResourceSync res) {
        resource = res;
    }
    
    public void run() {
        //get value, sleep, increment value
        for(; ; ) {
            System.out.println("Incrementing from " + resource.getValue());
            int sleep = 10 + (int) (Math.random() * 190);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted.");
            }
            //set value after sleeping
            resource.setValue(true);
        }
    }
}

/**
 * this class will decrement whatever value is in Resource.value
 */
class Decrement4 extends Thread {
    ResourceSync resource;
    
    public Decrement4(ResourceSync res) {
        resource = res;
    }
    
    public void run() {
        //get value, decrement value, sleep
        for(; ; ) {
            System.out.println("Decrementing from " + resource.getValue());
            //set value then sleep
            resource.setValue(false);
            int sleep = 10 + (int) (Math.random() * 190);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted.");
            }
        }
    }
}