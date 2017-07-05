package assignment_week_1.union_find;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
	private WeightedQuickUnionUF wquf;
	private int top;
	private int bottom;
	private int len;
	private boolean[][] opened;
	private int openSite = 0;
	public Percolation(int n) // create n-by-n grid, with all sites blocked
	{
		if (n <= 0)
			throw new IllegalArgumentException();
		wquf = new WeightedQuickUnionUF(n * n + 2);
		top = n * n;
		bottom = n * n + 1;
		len = n;
		opened = new boolean[n][n];
	}
	public void open(int row, int col) // open site (row, col) if it is not open already
	{
		if (!checkBoundry(row, col))
			throw new IndexOutOfBoundsException();

		if (!isOpen(row, col)) {
			openSite++;
			opened[row - 1][col - 1] = true;
		}
		if (row == 1)
			wquf.union(getIndex(row, col), top);
		if (row == len)
			wquf.union(getIndex(row, col), bottom);
		if (row > 1 && isOpen(row - 1, col))
			wquf.union(getIndex(row, col), getIndex(row - 1, col));
		if (row < len && isOpen(row + 1, col))
			wquf.union(getIndex(row, col), getIndex(row + 1, col));
		if (col > 1 && isOpen(row, col - 1))
			wquf.union(getIndex(row, col), getIndex(row, col - 1));
		if (col < len && isOpen(row, col + 1))
			wquf.union(getIndex(row, col), getIndex(row, col + 1));
	}
	private int getIndex(int row, int col) {
		// TODO Auto-generated method stub
		return (row - 1) * len + (col - 1);
	}
	public boolean isOpen(int row, int col) // is site (row, col) open?
	{
		if (checkBoundry(row, col))
			return opened[row - 1][col - 1];
		throw new IndexOutOfBoundsException();
	}
	private boolean checkBoundry(int row, int col) {
		// TODO Auto-generated method stub
		if (row < 1 || row > len || col < 1 || col > len)
			return false;
		return true;
	}
	public boolean isFull(int row, int col) // is site (row, col) full?
	{
		if (checkBoundry(row, col))
			return wquf.connected(getIndex(row, col), top);
		throw new IndexOutOfBoundsException();
	}
	public int numberOfOpenSites() // number of open sites
	{
		return openSite;
	}
	public boolean percolates() // does the system percolate?
	{
		return wquf.connected(top, bottom);
	}
}
