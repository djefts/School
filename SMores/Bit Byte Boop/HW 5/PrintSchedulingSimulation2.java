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
public class PrintSchedulingSimulation2 {
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
        PrintSchedulingSimulation.runSimulation(rand, minTimeRequired, maxTimeRequired, numIterations, probOfArrivalInOneUnit);
        
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
}//end class
