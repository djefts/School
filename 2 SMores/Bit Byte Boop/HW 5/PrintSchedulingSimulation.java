import java.util.Random;

/**
 * @author dgb
 * Date: 11/23/2018
 * This program does a simple simulation of a print server serving print jobs
 * that have priorities of 1 (highest), 2, or 3 (lowest). It uses a min heap to
 * queue the jobs arriving at printer. Each iteration of the for loop in the
 * runSimulation method is considered to be a unit of time and something happens
 * within that unit of time. Probabilities are used to model the random way
 * that events occur and to determine both the priority of the job and the
 * amount of units of time that the job is required. Certain statistics are
 * calculated and displayed after the loop exits.
 * Each job is represented by an object of the PrintJob class.
 */
public class PrintSchedulingSimulation {
    public static void main(String[] args) {
        Random rand = new Random(System.currentTimeMillis());
        double avgNumUnitsPerJobArrival = 3;
        double probOfArrivalInOneUnit = (1.0 / avgNumUnitsPerJobArrival)
                * Math.exp(-1.0 / avgNumUnitsPerJobArrival);
        System.out.println("Probability of arrival in one unit of time: " +
                probOfArrivalInOneUnit);
        int minTimeRequired = 3;
        int maxTimeRequired = 8;
        int numIterations = 30;
        
        runSimulation(rand, minTimeRequired, maxTimeRequired, numIterations, probOfArrivalInOneUnit);
    }//end main
    
    public static void runSimulation(Random rand, int minTimeRequired, int maxTimeRequired, int numIterations, double probOfArrival) {
        //keeps track of the number of jobs of the specified priority
        int[] priorityJobCounters = new int[3];
        init(priorityJobCounters);  //set all elements in the array to 0
        Printer printer = new Printer("Printer");
        for(int numTimes = 0; numTimes < numIterations; numTimes++) {
            if(rand.nextDouble() < probOfArrival) { //check to see if a job should arrive
                //Create the necessary info for that job.
                int jobPriority = getJobPriority(rand.nextDouble());
                priorityJobCounters[jobPriority - 1]++;
                int printTime = rand.nextInt(maxTimeRequired) + minTimeRequired;
                //Create the job with that info.
                PrintJob job = new PrintJob(jobPriority, printTime);
                //Immediately add the job to the queue.
                printer.printQ.add(job);
                System.out.println(job);
            }
            
            printer.next();
        }
        
        //display statistics about the simulation
        printStats(priorityJobCounters);
        System.out.println(printer);
    }
    
    public static void init(int[] numPriorJobs) {
        //Initializes the integer array to all zeros.
        for(int i = 0; i < numPriorJobs.length; i++) {
            numPriorJobs[i] = 0;
        }
    }
    
    public static int getJobPriority(double r) {
        //Returns 1, 2, or 3 depending on their expected frequency.
        if(r < 0.15)  //These jobs occur 15% of the time
            return 1;
        else if(r < 0.5) //These jobs occur 35% of the time
            return 2;
        else //These jobs occur 50% of the time
            return 3;
    }
    
    public static void printStats(int[] priorityJobCounters) {
        //Prints some stats about the simulation. The variables are self-documenting.
        System.out.println();
        int totalJobs = 0;
        for(int i = 0; i < priorityJobCounters.length; i++) {
            totalJobs += priorityJobCounters[i];
            System.out.println("Number of priority " + (i + 1) + " jobs: " + priorityJobCounters[i]);
        }
        System.out.println("Total jobs: " + totalJobs + "\n");
    }
}//end class
