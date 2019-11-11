/**
 * @author dgb
 */
/*
    A node that will be placed on a linked list.
    It is a generic class.
*/

public class OurList<T> {
    private LinkNode<T> head;
    private int numNodes;

    public OurList() {
        head = null;
        numNodes = 0;
    }

    public int size() {
        return numNodes;
    }

    public boolean isEmpty() {
        return numNodes == 0;
    }

    public void add(T data) {
        if(data == null) return;
        LinkNode<T> node = new LinkNode(data);
        numNodes++;
        if(head == null) {
            head = node;
            return;
        }

        LinkNode<T> currNode = head;
        while(currNode.next != null) {
            currNode = currNode.next;
        }

        currNode.next = node;
    }

    public void addToFront(T data) {
        if(data == null) return;
        LinkNode<T> node = new LinkNode<>(data);

        if(head == null) {
            head = null;
            return;
        }

        node.next = head;
        head = node;
    }

    public T get(int index) //gets data of node at specified index
    {
        if(index < 0 || index >= numNodes)
            throw new IndexOutOfBoundsException("No element at " + index);
        LinkNode<T> currNode = head;
        for(int i = 0; i < index; i++) {
            currNode = currNode.next;
        }

        return currNode.data;
    }

    public T remove(int index) //removes node at specified index and returns 
    //data
    {
        if(index < 0 || index >= numNodes)
            throw new IndexOutOfBoundsException("No element at " + index);

        numNodes--;
        LinkNode<T> prevNode = null;
        LinkNode<T> currNode = head;
        for(int i = 0; i < index; i++) {
            prevNode = currNode;
            currNode = currNode.next;
        }

        if(prevNode == null) //you are removing the head
        {
            head = currNode.next;
        } else {
            prevNode.next = currNode.next;
        }
        return currNode.data;
    }

    public void clear() {
        head = null;
        numNodes = 0;
    }

    /****************************/
    private class LinkNode<T> {
        private T data;
        private LinkNode<T> next;

        private LinkNode(T data) {
            this.data = data;
            next = null;
        }


    }
}