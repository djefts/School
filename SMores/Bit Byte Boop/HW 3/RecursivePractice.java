import java.util.ArrayList;

public class RecursivePractice {
    public static void main(String[] args) {
        System.out.println(fibonacci(10));
        System.out.println(fib(10));
        System.out.println(fibList(10) + "\n");
        
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        for(int num : array) System.out.print(num);
        System.out.println();
        reverse(array, 0, array.length - 1);
        for(int num : array) System.out.print(num);
        System.out.println("\n\n");
        
        allStrings("a");
    }
    
    public static int fibonacci(int n) {
        if(n==0) {
            return 0;
        } else if(n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    public static int fib(int n) {
        if(n == 0) {
            return 0;
        } else if(n == 1) {
            return 1;
        }
    
        int num1 = 0;
        int num2 = 1;
        int sum = 0;
        for(int i = 1; i < n; i++) {
            sum = num1 + num2;
            num1 = num2;
            num2 = sum;
        }
        return sum;
    }
    
    public static int fibList(int n) {
        int[] fibNums = new int[100];
        fibNums[0] = 0;
        fibNums[1] = 1;
        for(int i = 2; i < fibNums.length; i++) {
            fibNums[i] = fibNums[i - 2] + fibNums[i - 1];
        }
        return fibNums[n];
    }
    
    public static void reverse(int[] array, int left, int right) {
        if(left > right) return;
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
        reverse(array, left + 1, right - 1);
    }
    
    public static void allStrings(String str) {
        if(str.length() ==0) return;
        ArrayList<String> strings = new ArrayList<>();
        allStrings(str, strings, str.length());
        for(int i = 0; i < strings.size(); i++) {
            System.out.println(strings.get(i));
        }
        System.out.println("Number of strings: " + strings.size());
    }
    
    public static void allStrings(String str, ArrayList<String> strings, int n) {
        if(n==1) { //base case
            //loop through input string, add each character to list
            for(int i = 0; i< str.length(); i++) {
                strings.add(""+str.charAt(i));
            }
            return;
        }
        
        allStrings(str, strings, n-1);
        
        ArrayList<String> temp = new ArrayList<>();
        int size = strings.size();
        //loop through each character in the string
        for(int pos = 0; pos < str.length(); pos++) {
            //loop through list of strings
            for(int i=0; i<size; i++) {
                //create temp string --- character in string + string in strings
                String tempStr = str.charAt(pos) + strings.get(i);
                if(!temp.contains(tempStr)) {
                    temp.add(tempStr);
                }
            }
        }
        
        strings.clear();
        strings.addAll(temp);
    }
}
