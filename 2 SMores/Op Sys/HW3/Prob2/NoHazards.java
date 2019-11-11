/**
 * @author David Jefts
 * @date 10/8/18
 * @course CS420 Operating Systems
 * @saving Make sure this file and the prob1.bat files are saved inside of the Prob1 folder
 * @runtime Double-Click on prob1.bat to run!
 */
public class NoHazards {
    public static void main(String[] args) {
        Resource2 resource = new Resource2();
        Increment2 thread1 = new Increment2(resource);
        Decrement2 thread2 = new Decrement2(resource);
        Thread A = new Thread(thread1);
        Thread B = new Thread(thread2);
        
        A.start();
        B.start();
    }
}

/**
 * this class will increment whatever value is in Resource.value
 */
class Increment2 extends Thread {
    Resource2 resource;
    
    public Increment2(Resource2 res) {
        resource = res;
    }
    
    public void run() {
        //get value, sleep, increment value
        int value;
        for(; ; ) {
            value = resource.getValue() + 1;
            System.out.println("\tIncrementing " + value);
            resource.setValue(value);
        }
    }
}

/**
 * this class will decrement whatever value is in Resource.value
 */
class Decrement2 extends Thread {
    Resource2 resource;
    
    public Decrement2(Resource2 res) {
        resource = res;
    }
    
    public void run() {
        //get value, decrement value, sleep
        int value;
        for(; ; ) {
            value = resource.getValue() - 1;
            System.out.println("\tDecrementing " + value);
            resource.setValue(value);
        }
    }
}