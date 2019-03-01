/*
This is a demo task.

Write a function:

class Practice { public int solution(int[] A); }

that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

Given A = [1, 2, 3], the function should return 4.

Given A = [−1, −3], the function should return 1.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].
 */

import java.util.ArrayList;
import java.util.Comparator;

class Practice {
    
    public static void main(String[] args ) {
        Practice solution = new Practice();
        solution.solution2(new int[]{1, 2, 3});
    }
    
    public int solution(int[] A) {
        // O(n^2)
        int min = 1;
        
        for(int i = 0; i < A.length; i++) {
            if(A[i] == min) {
                min++;
                i = 0;
            }
        }
        
        return min;
    }
    
    public int solution1(int[] A) {
        // O(n*log(n))
        int min = 1;
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i<A.length; i++) {
            arr.add(A[i]);
        }
        arr.sort(Comparator.naturalOrder());
        for(int i : arr) {
            if(i > 0 && i==min) {
                min++;
            }
        }
        return min;
    }
    
    public int solution2(int[] A) {
        sort(A, 0, A.length);
        int min = 1;
        for(int i = 0; i < A.length; i++) {
            if(A[i] > 0) {
                if(A[i] == min) {
                    min++;
                }
            }
        }
        return min;
    }
    
    public void merge(int[] A, int l, int r, int m) {
        int s1 = m - l + 1;
        int s2 = r - m;
        
        int[] left = new int[s1];
        int[] right = new int[s2];
        
        for(int i = 0; i < s1; ++i)
            left[i] = A[l + i];
        for(int j = 0; j < s2; ++j)
            right[j] = A[m + 1 + j];
        
        int i = 0;
        int j = 0;
        int k = 0;
        
        while(i < s1 && j < s2) {
            if(left[i] <= right[j]) {
                A[k] = left[i];
                i++;
            } else {
                A[k] = right[j];
            }
            k++;
        }
        
        while(i < s1) {
            A[k] = left[i];
            k++;
            i++;
        }
        while(j < s2) {
            A[k] = right[j];
            k++;
            j++;
        }
    }
    
    public void sort(int[] A, int l, int r) {
        if(l < r) {
            int m = (l + r) / 2;
            sort(A, l, m);
            sort(A, m + 1, r);
            merge(A, l, r, m);
        }
    }
}