/**
 *
 * @author dgb
 */
public interface OurQueue<T> {
    T add(T data);  //Add element to 'back' of queue and return the parameter
    T remove();  //Remove element from 'front' of queue and return it.
    T peek(); //Return element at 'front' of queue without removing it
    int size();  //Return number of elements on the queue
    boolean isEmpty(); //Return true if queue is empty, false otherwise.
    void clear(); //Resets the queue.
}
