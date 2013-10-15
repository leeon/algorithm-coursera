import java.util.Iterator;
import java.util.NoSuchElementException;

/******************************************************************************
 * Author: Li Yang Username: leeon Date: 2013-10-15
 * 
 * Compilation: javac Deque.java
 * 
 * A double-ended queue for week2 Assignment
 ******************************************************************************/

public class Deque<Item> implements Iterable<Item> {

    // DataType for LinkedList
    private class Node {
        public Item item;
        public Node next;
        public Node prev;
    }

    private int N;

    private Node sentinel;

    // construct an empty deque
    public Deque() {
        sentinel = new Node();
        sentinel.item = null;
        sentinel.next = sentinel; // this makes a self-loop
        sentinel.prev = sentinel;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (N == 0);
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node newFirst = new Node();
        newFirst.item = item;
        // link the new node to right and left node
        newFirst.next = sentinel.next;
        newFirst.prev = sentinel;

        // insert the new first
        sentinel.next.prev = newFirst; // new<--pre when there is only sentinel ,a loop is made 
        sentinel.next = newFirst; // next-->new

        N++;
    }

    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node newLast = new Node();
        newLast.item = item;

        newLast.prev = sentinel.prev;
        newLast.next = sentinel;
        // same like addFirst
        sentinel.prev.next = newLast;
        sentinel.prev = newLast;

        N++;

    }

    // delete and return the item at the front
    public Item removeFirst() {
        if (N == 0) {
            throw new NoSuchElementException();// the queue is empty
        }
        Item item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel; // remember the double links, both next
                                       // and prev

        N--;

        return item;
    }

    // delete and return the item at the end
    public Item removeLast() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        Item item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        N--;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = sentinel;

        @Override
        public boolean hasNext() {
            return (current.next != sentinel);
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            current = current.next;
            return current.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}