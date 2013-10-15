import java.util.Iterator;
import java.util.NoSuchElementException;

/******************************************************************************
 * Author: Li Yang Username: leeon Date: 2013-10-15
 * 
 * Compilation: javac RandomizedQueue.java
 * 
 * A Random queue for week2 Assignment
 ******************************************************************************/

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int N = 0;
    private Item[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
    }

    // is the queue empty?
    public boolean isEmpty() {
        return (N == 0);
    }

    // return the number of items on the queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (N == queue.length) {
            resize(2 * queue.length);
        }
        queue[N++] = item;
    }

    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int choose = StdRandom.uniform(0, N); // random choose one
        Item item = queue[choose];
        queue[choose] = queue[--N];
        queue[N] = null;

        if (N > 0 && N == queue.length / 4) { //N > 0 is important 
            resize(queue.length / 2);
        }

        return item;
    }

    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int choose = StdRandom.uniform(0, N); // random choose one
        Item item = queue[choose];
        return item;
    }

    // resize the queue
    private void resize(int size) {
        Item[] newQueue = (Item[]) new Object[size];
        for (int i = 0; i < N; i++) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int current = 0;
        private Item[] result;

        public ListIterator() {
            result = (Item[]) new Object[N];
            for (int i = 0; i < N; i++) {
                result[i] = queue[i];
            }
            StdRandom.shuffle(result);
        }

        @Override
        public boolean hasNext() {
            return (current != N);
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return result[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}