import sun.awt.image.ImageWatched;

/**
 * @param <T>
 * @author dgb
 */
public class OurLinkedQueue<T> implements OurQueue<T> {
    private LinkNode<T> head; //Keeps track of the front of the queue.
    private LinkNode<T> tail;  //Keeps track of the back of the queue.
    private int count;

    public OurLinkedQueue() {
        clear();
    }

    @Override
    public T add(T data) { //Add element to 'rear' of queue and return the parameter
        if(data == null) { //make sure we actually have data to add
            return null;
        }

        //now we are actually adding something to the end of the queue
        if(count == 0) { //if there is nothing already in the queue then tail and head will be same
            head = tail = new LinkNode<>(data);
            count++;
            return data;
        }

        //set the last node and tail to the node being added
        LinkNode<T> node = new LinkNode<>(data);
        tail.next = node;
        tail = node;

        count++;
        return data;
    }

    @Override
    public T remove() {  //Remove element from 'front' of queue and return it.
        if(head == null) { //make sure we actually have data to remove
            return null;
        }

        //now we are actually popping off the queue
        if(count == 1) { //only one element in queue
            LinkNode<T> removed = head; //get the one element
            clear(); //reset the queue
            return removed.data;
        }

        //removed node
        LinkNode<T> removed = head;
        //set head to the next node
        head = head.next;
        count--;
        return removed.data;
    }

    @Override
    public T peek() { //Return element at 'front' of queue without removing it
        if(head == null) return null;
        return head.data;
    }

    @Override
    public int size() { //Return number of elements on the queue
        return count;
    }

    @Override
    public boolean isEmpty() { //Return true if queue is empty, false otherwise.
        return head == null;
    }

    @Override
    public void clear() { //Resets the queue.
        head = tail = null;
        count = 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("head -->\t");
        for(int i = 0; i < count; i++) {
            stringBuilder.append(add(remove()));
            stringBuilder.append("\t");
        }
        stringBuilder.append("<-- tail");
        return stringBuilder.toString();
    }


    //Inner Class
    private class LinkNode<T> {
        private T data;
        private LinkNode<T> next;

        private LinkNode(T data) {
            this.data = data;
            next = null;
        }

    }
}//End LinkedStack
