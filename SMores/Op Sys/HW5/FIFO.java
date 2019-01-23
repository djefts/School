/**
 * David Jefts
 * Operating Systems - CS420
 *
 * First In First Out (F.I.F.O.) algorithm for page replacement
 * Simulates a page replacement function in an operating system
 *
 * This class is automatically called using the Test class file
 * Usage:
 * FIFO fifo = new FIFO(numberOfPageFrames);
 * fifo.insert(pageFrameNumber);
 */

public class FIFO extends ReplacementAlgorithm {
    
    public static void main(String[] args) {
        FIFO fifo = new FIFO(7);
        fifo.insert(4);
    }
    
    public FIFO(int pageFrameCount) {
        super(pageFrameCount);
    }
    
    @Override
    public void insert(int pageNumber) {
        if(pageNumber < 0) return; //kill method if pageNumber is invalid
        
        PageGenerator gen = new PageGenerator(pageFrameCount);
        int[] ref = gen.getReferenceString();
        
        //search entire list for the new pageNumber
        boolean inPage = false;
        for(int i = 0; i < ref.length; i++) {
            if(pageNumber == ref[i]) {
                inPage = true;
            }
        }
        
        //insert the page if not already existing in ref, then increase pageFaultCount
        if(!inPage) {
            actualInsert(pageNumber, ref);
        }
    }
    
    public void actualInsert(int pageNumber, int[] ref) {
        System.out.println("FIFO inserting page " + pageNumber);
        for(int i = 0; i < ref.length - 1; i++) {
            ref[i] = ref[i + 1];
        }
        ref[ref.length - 1] = pageNumber;
        pageFaultCount++;
    }
}
