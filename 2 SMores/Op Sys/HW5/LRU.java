/**
 * David Jefts
 * Operating Systems - CS420
 *
 * Least Recently Used (L.R.U.) algorithm for page replacement
 * Simulates a page replacement function in an operating system
 *
 * This class is automatically called using the Test class file
 * Usage:
 * LRU lru = new LRU(numberOfPageFrames);
 * lru.insert(pageFrameNumber);
 */

import java.util.ArrayList;

public class LRU extends ReplacementAlgorithm {
    int[] uses = new int[PageGenerator.getRANGE() + 1];
    
    public LRU(int pageFrameCount) {
        super(pageFrameCount);
    }
    
    @Override
    public void insert(int pageNumber) {
        if(pageNumber < 0) return; //kill method if pageNumber is invalid
        
        PageGenerator gen = new PageGenerator(pageFrameCount);
        int[] ref = gen.getReferenceString();
        uses[pageNumber]++;
        
        //search entire list for the new pageNumber
        boolean inPage = false;
        for(int i = 0; i < ref.length; i++) {
            if(pageNumber == ref[i]) {
                inPage = true;
                break;
            }
        }
        
        //insert the page if not already existing in ref, then increase pageFaultCount
        if(!inPage) {
            actualInsert(pageNumber, ref);
        }
    }
    
    public void actualInsert(int pageNumber, int[] ref) {
        System.out.println("LRU  inserting page " + pageNumber);
        
        //find all pages with least num uses
        int minUses = 0; //least calls on a given page
        ArrayList<Integer> pageReplace = new ArrayList<>(); //list of pages with low number of calls
        for(int i = 0; i < uses.length; i++) {
            if(uses[i] <= minUses) {
                pageReplace.add(i);
                minUses = uses[i];
            }
        }
        
        //clean up pageReplace
        int x = minUses;
        pageReplace.removeIf(i -> i < x);
        
        //replace ref[pageReplace] with pageNumber
        for(int i = 0; i < ref.length; i++) {
            if(pageReplace.contains(ref[i])) {
                ref[i] = pageNumber;
                break;
            }
        }
        pageFaultCount++;
    }
}
