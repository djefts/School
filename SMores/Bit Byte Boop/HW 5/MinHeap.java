
import java.util.ArrayList;

/**
 *
 * @author dgb
 This class implements a MinHeap data structure using arrays.
 * @param <T>
 */
public class MinHeap<T extends Comparable<T>> {
    private T[] heap;
    private int size;
    public static final int DEFAULT_CAPACITY = 10;
    
    public MinHeap(int capacity)
    {
        if (capacity <= 0)
            capacity = DEFAULT_CAPACITY;
        heap = (T[]) new Comparable[capacity];
        size = 0;
    }
    public int size(){ return size; }
    
    public void add(T data)
    {
        if(data == null) return;
        if(size == heap.length)
            doubleCapacity();
        heap[size] = data;
        if(size > 0)  // you are not the root
        {
            //Move data into correct position in heap
            int currPos = size;
            int posOfParent = (currPos - 1)/2;
            while(currPos > 0)
            {
                if (heap[posOfParent].compareTo(heap[currPos]) > 0) //parent is bigger
                //swap them
                {
                    T temp = heap[posOfParent];
                    heap[posOfParent] = heap[currPos];
                    heap[currPos] = temp;
                    currPos = posOfParent;
                    posOfParent = (currPos - 1)/2;
                }
                else
                {
                    break;
                }
            }
        }
        size++;
    }

    public T remove() //removes from the top of the heap
    {
        if (isEmpty()) return null;  //nothing on heap
        T data = heap[0];
        heap[0] = heap[size-1]; //copy the last one into the first position
        size--;  //drop the size by 1. This effectively removes the last available
        if (size == 0) return data; //that was the last one
        
        //position
        //Bubble the top down into its correct spot.
        int pos = 0;
        boolean finished = false;
        while (!finished)
        {
            int leftChildPos = 2 * pos + 1;
            int rightChildPos = 2 * pos + 2;
            int posToMoveTo = pos;  //need this assignment so that the compiler doesn't complain
            finished = true;
            if(leftChildPos < size && rightChildPos < size)  // two children
            {
                if (heap[leftChildPos].compareTo(heap[rightChildPos]) < 0)
                {//left child is smaller
                    if(heap[pos].compareTo(heap[leftChildPos]) > 0)
                    {
                        posToMoveTo = leftChildPos;
                        finished = false;
                    }
                }
                else
                {
                    if(heap[pos].compareTo(heap[rightChildPos]) > 0)
                    {
                        posToMoveTo = rightChildPos;
                        finished = false;
                    }
                }
            }
            else if (leftChildPos < size)  //only left child
            {
                if(heap[pos].compareTo(heap[leftChildPos]) > 0)
                {
                    posToMoveTo = leftChildPos;
                    finished = false;
                }                
            }
            else if (rightChildPos < size) //only right child
            {
                if(heap[pos].compareTo(heap[rightChildPos]) > 0)
                {
                    posToMoveTo = rightChildPos;
                    finished = false;
                }                
            }
            if(!finished)  //swap them
            {
                T temp = heap[pos];
                heap[pos] = heap[posToMoveTo];
                heap[posToMoveTo] = temp;
                pos = posToMoveTo;  //new position to start from
            }
        }
        return data;
    }
    
    public boolean isEmpty(){ return size == 0; }
    
    public String[] getHeapArray(){
        String[] dataArray = new String[size];
        for(int i = 0; i < size; i++)
        {
            dataArray[i] = heap[i].toString();
        }
        return dataArray; 
    }
    
    public ArrayList<T> heapSort()
    {
        ArrayList<T> list = new ArrayList();
        T[] heapCopy = (T[]) new Comparable[heap.length];
        System.arraycopy(heap, 0, heapCopy, 0, heap.length);
        int sizeCopy = size;
        while(!isEmpty())
        {
            T data = remove();
            list.add(data);
        }
        heap = heapCopy;
        size = sizeCopy;
        return list;
    }
    
    private void doubleCapacity()
    {
        T[] heapCopy = (T[]) new Comparable[2*heap.length];
        System.arraycopy(heap, 0, heapCopy, 0, heap.length);        
        heap = heapCopy;
    }
}
