import java.util.Random;
import java.util.Scanner;

/**
 * @author David Jefts
 * Date: 11/27/2018
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
public class PrintSchedulingSimulation3 {
    public static void main(String[] args) {
        master(args);
    }//end main
    
    public static void master(String[] args) {
        //user enters avgNumUnitsPerJobArrival, minTimeRequired, maxTimeRequired, and numIterations
        Random rand = new Random(System.currentTimeMillis());
        Scanner scanner = new Scanner(System.in);
        double avgNumUnitsPerJobArrival = getNumUnits(scanner);
        double probOfArrivalInOneUnit = (1.0 / avgNumUnitsPerJobArrival) * Math.exp(-1.0 / avgNumUnitsPerJobArrival);
        System.out.println("Probability of arrival in one unit of time: " + probOfArrivalInOneUnit);
        int minTimeRequired = getMinTime(scanner);
        int maxTimeRequired = getMaxTime(scanner, minTimeRequired);
        int numIterations = getIterations(scanner);
        
        if(numIterations == 0) {
            System.out.println("\n\nThank you for playing. Come again!");
            System.exit(0);
        }
        
        System.out.println("\n\n");
        PrintSchedulingSimulation3.runSimulation(rand, minTimeRequired, maxTimeRequired, numIterations, probOfArrivalInOneUnit);
        
        System.out.println("Rerunning simulation...");
        master(args);
    }
    
    public static double getNumUnits(Scanner scan) {
        System.out.print("\nPlease enter the average number of units per job.\t");
        double dub = scan.nextDouble();
        scan.nextLine();
        if(dub < 0) {
            System.out.println("You entered an invalid number. Please try again.");
            dub = getNumUnits(scan);
        }
        return dub;
    }
    
    public static int getMinTime(Scanner scan) {
        System.out.print("\nPlease enter the minimum amount of time required per job.\t");
        int tim = scan.nextInt();
        scan.nextLine();
        if(tim < 0) {
            System.out.println("You entered an invalid number. Please try again");
            tim = getMinTime(scan);
        }
        return tim;
    }
    
    public static int getMaxTime(Scanner scan, int minTime) {
        System.out.print("\nPlease enter the maximum amount of time required per job.\t");
        int tim = scan.nextInt();
        scan.nextLine();
        if(tim < minTime) {
            System.out.println("You entered an invalid number. Please try again");
            tim = getMaxTime(scan, minTime);
        }
        return tim;
    }
    
    public static int getIterations(Scanner scan) {
        System.out.print("\nPlease enter the number of iterations. Enter 0 to end the simulation.\t");
        int its = scan.nextInt();
        scan.nextLine();
        if(its < 0) {
            System.out.println("You entered an invalid number. Please try again");
            its = getIterations(scan);
        }
        return its;
    }
    
    public static void runSimulation(Random rand, int minTimeRequired, int maxTimeRequired, int numIterations, double probOfArrival) {
        //keeps track of the number of jobs of the specified priority
        int[] priorityJobCounters = new int[3];
        init(priorityJobCounters);  //set all elements in the array to 0
        Printer printerOne = new Printer("Printer One");
        Printer printerTwo = new Printer("Printer Two");
        for(int numTimes = 0; numTimes < numIterations; numTimes++) {
            if(rand.nextDouble() < probOfArrival) { //check to see if a job should arrive
                //Create the necessary info for that job.
                int jobPriority = getJobPriority(rand.nextDouble());
                priorityJobCounters[jobPriority - 1]++;
                int printTime = rand.nextInt(maxTimeRequired) + minTimeRequired;
                //Create the job with that info.
                PrintJob job = new PrintJob(jobPriority, printTime);
                
                //Immediately add the job to the queue of a free printer.
                if(printerOne.isFree()) {
                    printerOne.printQ.add(job);
                } else if(printerTwo.isFree()) {
                    printerTwo.printQ.add(job);
                } else { //if no free printers, randomly pick one
                    if(Math.random() < 0.5) {
                        printerOne.printQ.add(job);
                    } else {
                        printerTwo.printQ.add(job);
                    }
                }
                
                System.out.println(job);
            }
            
            printerOne.next();
            printerTwo.next();
        }
        
        //display statistics about the simulation
        PrintSchedulingSimulation.printStats(priorityJobCounters);
        System.out.println(printerOne + "\n" + printerTwo);
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
}