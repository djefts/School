public class SortingComparisons {
    static int[] solvedBubble;
    static int[] solvedMerge;
    static int[] solvedQuick;
    
    public static void main(String[] args) {
        int[] Obubble = new int[4];
        int[] Omerge = new int[4];
        int[] Oquick = new int[4];
        for(int i = 1; i < 5; i++) {
            int size = (int) Math.pow(10, i);
            //create arrays for the three sorters
            int[] bubbleArr = new int[size];
            int[] mergeArr = new int[size];
            int[] quickArr = new int[size];
            
            for(int j = 0; j < size; j++) {
                //create a random number, add it to all 3 arrays
                int rand = (int) (Math.random() * 10);
                bubbleArr[j] = rand;
                mergeArr[j] = rand;
                quickArr[j] = rand;
            }
            //System.out.println("Starting array: " + printArray(bubbleArr));
            Obubble[i - 1] = bubbleSort(bubbleArr);
            //System.out.println("After bubbleSort: " + printArray(solvedBubble));
            Omerge[i - 1] = mergeSort(mergeArr, 0, mergeArr.length - 1);
            //System.out.println("After merge sort: " + printArray(solvedMerge));
            Oquick[i - 1] = quickSort(quickArr, 0, quickArr.length - 1);
            //System.out.println("After quick sort: " + printArray(solvedQuick));
        }
        System.out.format("%20s%20s%20s%20s\n", "Number of Elements", "Bubble Sort", "Merge Sort", "Quick Sort");
        System.out.format("%20d%20d%20d%20d\n", 10, Obubble[0], Omerge[0], Oquick[0]);
        System.out.format("%20d%20d%20d%20d\n", 100, Obubble[1], Omerge[1], Oquick[1]);
        System.out.format("%20d%20d%20d%20d\n", 1000, Obubble[2], Omerge[2], Oquick[2]);
        System.out.format("%20d%20d%20d%20d\n", 10000, Obubble[3], Omerge[3], Oquick[3]);
    }
    
    public static int bubbleSort(int[] array) {
        int count = 0;
        
        int n = array.length;
        for(int i = 0; i < array.length - 1; i++) {
            for(int j = 1; j <= n - i - 1; j++) {
                if(array[j - 1] > array[j]) {
                    //swap
                    count++;
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                    //end swap
                }
            }
        }
        solvedBubble = array;
        return count;
    }
    
    public static int mergeSort(int[] array, int l, int r) {
        int count = 0;
        if(l < r) {
            int m = (l + r) / 2;
            //sort the first half of the array
            count += mergeSort(array, l, m);
            //sort the second half of the array
            count += mergeSort(array, m + 1, r);
            //merge the sorted left and right halves
            count += merge(array, l, m, r);
        }
        solvedMerge = array;
        return count;
    }
    
    public static int merge(int[] array, int l, int m, int r) {
        int count = 0;
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
        
        /* Create temp arrays */
        int[] L = new int[n1];
        int[] R = new int[n2];
        
        /*Copy data to temp arrays*/
        for(int i = 0; i < n1; ++i)
            L[i] = array[l + i];
        for(int j = 0; j < n2; ++j)
            R[j] = array[m + 1 + j];
        
        
        /* Merge the temp arrays */
        
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
        
        // Initial index of merged subarray array
        int k = l;
        while(i < n1 && j < n2) {
            if(L[i] <= R[j]) {
                count++;
                array[k] = L[i];
                i++;
            } else {
                count++;
                array[k] = R[j];
                j++;
            }
            k++;
        }
        
        /* Copy remaining elements of L[] if any */
        while(i < n1) {
            //help me
            array[k] = L[i];
            i++;
            k++;
        }
        
        /* Copy remaining elements of R[] if any */
        while(j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
        return count;
    }
    
    public static int quickSort(int[] array, int l, int r) {
        int count = 0;
        //System.out.println(printArray(array));
        if(l < r) {
            count++;
            int[] stuff = pivot(array, l, r);
            int p = stuff[0];
            count += stuff[1];
            count += quickSort(array, l, p - 1); //left of pivot
            count += quickSort(array, p + 1, r); //right of pivot
        }
        solvedQuick = array;
        return count;
    }
    
    public static int[] pivot(int[] array, int l, int r) {
        int count = 0;
        
        int pivot = array[r];
        int i = (l - 1); // index of smaller element
        for(int j = l; j < r; j++) {
            // If current element is smaller than or equal to pivot
            if(array[j] <= pivot) {
                count++;
                //swap
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                //end swap
            }
        }
        
        //swap
        count++;
        int temp = array[i + 1];
        array[i + 1] = array[r];
        array[r] = temp;
        //end swap
        
        return new int[]{i + 1, count};
    }
    
    public static String printArray(int[] array) {
        String output = "[";
        for(int i = 0; i < array.length - 1; i++) {
            output += array[i] + ", ";
        }
        return output + array[array.length - 1] + "]";
    }
}
