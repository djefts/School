/**
 *
 * @author dgb
 */
public interface OurStack<T> {
    T push(T data);  //Add element to 'top' of stack and return the parameter
    T pop();  //Remove element from 'top' of stack and return it.
    T peek(); //Return element at 'top' of stack without removing it
    int size();  //Return number of elements on the stack
    boolean isEmpty(); //Return true if stack is empty, false otherwise.
    void clear(); //Resets the stack.
}
