/**
 * David Jefts
 * Operating Systems - CS420
 *
 * Test harness for LRU and FIFO page replacement algorithms
 * Main method class to test the functionality and correctness of the LRU and FIFO classes
 *
 * Run the given batch file to execute this program
 * <p>
 * Command line usage:
 * java Test <reference string size> <number of page frames>
 * Batch file usage:
 * Change the line `java Test 30 5` to `java Test <reference string size> <number of page frames>`
 */

public class Test {
    public static void main(String[] args) {
        PageGenerator gen = new PageGenerator(Integer.parseInt(args[0]));
        int[] referenceString = gen.getReferenceString();
        
        /* Use either the FIFO or LRU algorithms */
        ReplacementAlgorithm fifo = new FIFO(Integer.parseInt(args[1]));
        ReplacementAlgorithm lru = new LRU(Integer.parseInt(args[1]));
        
        // output a message when inserting a page
        for(int i = 0; i < referenceString.length; i++) {
            //System.out.println("inserting " + referenceString[i]);
            lru.insert(referenceString[i]);
        }
        
        // output a message when inserting a page
        for(int i = 0; i < referenceString.length; i++) {
            //System.out.println("inserting " + referenceString[i]);
            fifo.insert(referenceString[i]);
        }
        
        // report the total number of page faults
        System.out.println("\nLRU faults = " + lru.getPageFaultCount());
        System.out.println("FIFO faults = " + fifo.getPageFaultCount());
        
        if(lru.getPageFaultCount() == 0 && fifo.getPageFaultCount() == 0) {
            System.out.println("\n\n\033[1;30mDISCLAIMER:\nYou probably need to change the range of values used in the PageGenerator class.");
        }
    }
}
