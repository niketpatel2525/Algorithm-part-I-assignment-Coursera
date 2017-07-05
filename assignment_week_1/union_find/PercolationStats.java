package assignment_week_1.union_find;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double[] result;
	private int trials;

	public PercolationStats(int n, int trials) // perform trials independent experiments on an n-by-n grid
	{
		if (n <= 0 || trials <= 0)
			throw new IllegalArgumentException();

		this.trials = trials;
		this.result = new double[trials];

		for (int i = 0; i < trials; i++) {

			Percolation p = new Percolation(n);
			while (!p.percolates()) {
				int row = StdRandom.uniform(1, n + 1);
				int col = StdRandom.uniform(1, n + 1);
				if (!p.isOpen(row, col) && !p.isFull(row, col))
					p.open(row, col);
			}
			int openSites = p.numberOfOpenSites();
			result[i] = (double) openSites / (n * n);

		}

	}

	public double mean() // sample mean of percolation threshold
	{
		return StdStats.mean(result);
	}

	public double stddev() // sample standard deviation of percolation threshold
	{
		return StdStats.stddev(result);
	}

	public double confidenceLo() // low endpoint of 95% confidence interval
	{
		return (mean() - (1.96 * stddev()) / Math.sqrt(trials));
	}

	public double confidenceHi() // high endpoint of 95% confidence interval
	{
		return (mean() + (1.96 * stddev()) / Math.sqrt(trials));
	}

	public static void main(String[] args) // test client (described below)
	{
		//PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		PercolationStats ps = new PercolationStats(3, 2);
		System.out.println("mean                    = " + ps.mean());
		System.out.println("stddev                  = " + ps.stddev());
		System.out.println("95% confidence interval = [" + ps.confidenceLo() + "," + ps.confidenceHi() + "]");
	}
}
