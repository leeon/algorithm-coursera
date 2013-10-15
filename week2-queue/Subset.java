/******************************************************************************
 * Author: Li Yang Username: leeon Date: 2013-10-15
 * 
 * Compilation: javac Subset.java
 * 
 * Subset Client for week2 Assignment
 ******************************************************************************/

public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }
        
        for (int i = 0; i < k; i++) {
            System.out.println(queue.dequeue());
        }

    }
}