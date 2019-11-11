class GenericStack<E> {
    public final static int INITIAL_SIZE = 16;
    private E[] elements;
    private int size;
    
    /**
     * Construct a stack with the default initial capacity
     */
    public GenericStack() {
        elements = ( E[] )new Object[INITIAL_SIZE];
    }
    
    /**
     * Construct a stack with the specified initial capacity
     */
    public GenericStack(int initialCapacity) {
        elements = ( E[] )new Object[initialCapacity];
    }
    
    /**
     * Push a new element into the top of the stack
     */
    public E push(E value) {
        size++;
        /* length of elements is doubled each time the number of elements is about to exceed the current maximum length */
        if(size >= elements.length) {
            E[] temp = ( E[] )new Object[elements.length * 2];
            
            // copy from elements[0:] to temp[0:] where temp.length == (elements.length * 2)
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;  // replace Stack with lengthened stack
        }
        return elements[size] = value;  // add the new value to the stack and return that value
    }
    
    /**
     * Return and remove the top element from the stack
     */
    public E pop() {
        E value = elements[size - 1];  // store topmost value
        elements[size - 1] = null;  // set it to null
        return value;
    }
    
    /**
     * Return the top element from the stack
     */
    public E peek() {
        return elements[size - 1];  // return without deleting the top element
    }
    
    /**
     * Return whether the stack is empty
     */
    public boolean isEmpty() {
        return size == 0;  // true if size == 0, false otherwise
    }
    
    /**
     * Return the number of elements in the stack
     */
    public int getSize() {
        return this.size;
    }
    
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < size; i++) {
            output.append(( String )elements[i]);
        }
        return output.toString();
    }
}