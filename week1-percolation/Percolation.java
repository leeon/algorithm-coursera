/******************************************************************************
 * Author: Li Yang
 * Username: leeon
 * Date: 2013-10-08
 *
 * Compilation: $ javac Percolation.java
 * Testing: $ java Percolation
 *
 * Data type for percolation on coursera week 1
 ******************************************************************************/
public class Percolation {

    private final int N;
    private boolean opened [ ]; //to mark if a site is opened
    
    private int virtualTop;
    private int virtualBottom; //to solve baskwash problem

    private WeightedQuickUnionUF percolation;
    private WeightedQuickUnionUF backwash;
    
    public Percolation(int n) {
        this.N = n;
        opened = new boolean[N*N]; 
        percolation = new WeightedQuickUnionUF(n*n+2); // n*n grid and two virtual points
        backwash = new WeightedQuickUnionUF(n*n+2); 
        virtualTop = locate(N, N)+1;
        virtualBottom = locate(N, N)+2;
        
    }
    
    /*open a site */
    public void open(int i, int j){
        
        if(isOpen(i,j)) return;
        
        int currentSite = locate(i, j); //low the cal
        if(i == 1) { //the top line
            percolation.union(currentSite, virtualTop);
            backwash.union(currentSite, virtualTop);
        }
        
        if(i == N) { //the bottom line 
            percolation.union(currentSite, virtualBottom);
        }
        
        if(i > 1 && isOpen(i-1, j)) {// the site have a top neighbor
            percolation.union(currentSite, locate(i-1, j));
            backwash.union(currentSite, locate(i-1, j));

        }
        
        if(i < N && isOpen(i+1, j)) {//the site have a bottom  neighbor
            percolation.union(currentSite, locate(i+1, j));
            backwash.union(currentSite, locate(i+1, j));

        }
        
        if(j > 1 && isOpen(i, j-1)) { //the site have a left neighbor
            percolation.union(currentSite, locate(i, j-1));
            backwash.union(currentSite, locate(i, j-1));

        }
        
        if(j < N && isOpen(i, j+1)) {//the site have a right neighbor
            percolation.union(currentSite, locate(i, j+1));
            backwash.union(currentSite, locate(i, j+1));

        }
        
        opened[locate(i, j)] = true;
        
    }
    
    /* is site (row i, column j) open?*/
    public boolean isOpen(int i, int j){
        return opened[locate(i, j)];
    }
    
    /*is site (row i, column j) full?*/
    public boolean isFull(int i, int j) {
        return backwash.connected(locate(i,j), virtualTop);
    }
    
    /*does the system percolate?*/
    public boolean percolates() {
        return percolation.connected(virtualTop, virtualBottom);
    }
    
    /*translate (x,y) to index of array*/
    private int locate(int row, int col) {
        if(row < 1 || row > N || col < 1 || col > N){
            throw new IndexOutOfBoundsException();
        }
        return ((row-1)*N + (col-1));
      
    }
    
    /*basic tests*/
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(1, 2);
        p.open(2, 2);
        p.open(2, 3);
        p.open(3, 3);
        p.open(4, 3);
        p.open(5, 3);
        
        if(p.percolates()) {
            System.out.println("it percolates!!");
        } else {
            System.out.println("blocked!");
        }
        
    }
    
}
