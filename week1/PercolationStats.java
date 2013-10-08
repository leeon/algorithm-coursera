/******************************************************************************
 * Author: Li Yang
 * Username: leeon
 * Date: 2013-10-08
 *
 * Compilation: $ javac PercolationStats.java
 * Testing: $ java PercolationStats
 *
 * Monte Carlo simulation for percolation on coursera week 1
 ******************************************************************************/
public class PercolationStats {
    
    private final int N; //mark N grid
    private double x [ ]; //fraction of open sites
    private final static double CONFIDENCE95 = 1.96; 
    
    public PercolationStats(int N , int T) {
        this.N = N;
        x = new double[T];
        
        if(N <= 0 || T <= 0) {
            throw new IllegalArgumentException("arguments");
        }
        Percolation p = null;
        for (int t = 0; t < T; t++) {
            p = new Percolation(N);
            int counter = 0; 
            while(!p.percolates()) { // run until the system percolates
                int i,j; //get X & Y random
                do {
                    i = StdRandom.uniform(1, N+1);
                    j = StdRandom.uniform(1, N+1);
                }while(p.isOpen(i, j));
                
                p.open(i, j); //open the random site
                counter++;
            }
            
            x[t] = ((double) counter)/(N*N);
        }
    }
    
    /*sample mean of percolation threshold*/
    public double mean() {
        return StdStats.mean(x);
    }
    
    /*sample standard deviation of percolation threshold*/
    public double stddev() {
        return StdStats.stddev(x);
    }
    
    /*returns lower bound of the 95% confidence interval*/
    public double confidenceLo() {
        return mean() - (stddev()*CONFIDENCE95)/Math.sqrt(x.length);
    }

    /*returns upper bound of the 95% confidence interval*/
    public double confidenceHi() {
        return mean() + (stddev()*CONFIDENCE95)/Math.sqrt(x.length);
    }
    
    // test client, described below
    public static void main(String[] args) {
       
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);           

        PercolationStats p = new PercolationStats(N,T);
        System.out.println("mean                    = " + p.mean());
        System.out.println("stddev                  = " + p.stddev());
        System.out.println("95% confidence interval = " + p.confidenceLo() + "," + p.confidenceHi());
    }   
}
