import java.util.ArrayList;

/**
 * Questions 4-5 TB pg 363-365
 */

public class BinaryCodes {
    
    public static void main(String[] args) {
        generateBinaryCode(4);
        System.out.println("\n");
        generateGrayCode(5);
    }
    
    //generate all binary numbers possible with nBits
    public static void generateBinaryCode(int nBits) {
        System.out.println("Binary codes for " + nBits + " bits:");
        ArrayList<String> codes = new ArrayList<>();
        generateBinaryCode(nBits, codes, nBits);
        for(String code : codes) {
            System.out.println(code);
        }
    }
    
    public static void generateBinaryCode(int nBits, ArrayList<String> codes, int n) {
        if(n == 1) {
            for(int i = 0; i < Math.pow(2, nBits); i++) {
                codes.add(i%2==0 ? "0" : "1");
            }
            return;
        }
        
        generateBinaryCode(nBits, codes, n - 1);
        
        ArrayList<String> temp = new ArrayList<>();
        for(int i = 0; i < Math.pow(2, nBits); i++) {
            for(String code : codes) {
                String tempStr = i % 2 + code;
                if(!temp.contains(tempStr)) {
                    temp.add(tempStr);
                }
            }
        }
        
        codes.clear();
        codes.addAll(temp);
    }
    
    public static void generateGrayCode(int nBits) {
        System.out.println("Gray codes for " + nBits + " bits:");
        ArrayList<String> codes = new ArrayList<>();
        generateGrayCode(nBits, codes, nBits);
        for(String code : codes) {
            System.out.println(code);
        }
    }
    
    public static void generateGrayCode(int nBits, ArrayList<String> codes, int n) {
        //base case
        if(n == 1) {
            codes.add("0");
            codes.add("1");
            return;
        }
        
        //gray code for N-1 bits
        generateGrayCode(nBits, codes, n - 1);
        
        //copy list in reverse order
        for(int i = codes.size()-1; i>=0; i--) {
            codes.add(codes.get(i));
        }
        
        //add 0 to first half and 1 to second half
        for(int i = 0; i<codes.size(); i++) {
            codes.set(i, i<codes.size()/2 ? codes.set(i, "0" + codes.get(i)) : codes.set(i, "1" + codes.get(i)));
        }
    }
}
