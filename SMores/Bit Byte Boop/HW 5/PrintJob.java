
import java.util.ArrayList;

/*
 * @author dgb
 * Date: 11/23/2018
 * Represents jobs going to a printer.
 *
 */
public class PrintJob implements Comparable<PrintJob> {
    //Only one copy of the following variable is created for the entire PrintJob class.
    //That is, no matter how many PrintJob objects are made there is only one
    //jobCounter variable.
    //It is used to generate a number for each object created.
    private static int jobCounter = 0;
    
    private int timeRequired;
    private int priority;  //1 (highest) - 3 (lowest)
    private int jobNumber;
    
    public PrintJob(int prior, int time) {
        jobCounter++;  //Every PrintJob object created gets its own unique number
        jobNumber = jobCounter;
        if(prior < 1 || prior > 3)
            prior = 3;
        priority = prior;
        if(time <= 0) time = 1;
        timeRequired = time;
    }
    
    public static void main(String[] args) {
        PrintJob job1 = new PrintJob(3, 30);
        PrintJob job2 = new PrintJob(2, 20);
        PrintJob job3 = new PrintJob(1, 10);
        System.out.println(job1);
        System.out.println(job2);
        System.out.println(job3);
        
        System.out.println("***************");
        MinHeap<PrintJob> jobQueue = new MinHeap(5);
        jobQueue.add(job1);
        jobQueue.add(job2);
        jobQueue.add(job3);
        
        System.out.println("Jobs sorted from highest to lowest priority.");
        ArrayList<PrintJob> jobs = jobQueue.heapSort();
        for(int i = 0; i < jobs.size(); i++) {
            System.out.println(jobs.get(i) + "\n*******************");
        }
    }//end main
    
    public int getJobNumber() {
        return jobNumber;
    }
    
    public int getTimeRequired() {
        return timeRequired;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public static int numJobsCreated() {
        return jobCounter;
    }
    
    @Override
    public int compareTo(PrintJob job) {
        if(job == null) return 1;
        if(priority < job.priority) return -1;
        if(priority > job.priority) return 1;
        return 0;
    }
    
    public String toString() {
        return "Job " + jobNumber + " with priority " + priority + " and print time " + timeRequired  + " sent to printer.";
    }
}//end class
