package assignment_week_3.sorting;

import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

	private final int x; // x-coordinate of this point
	private final int y; // y-coordinate of this point

	public Point(int x, int y) // constructs the point (x, y)
	{
		this.x = x;
		this.y = y;

	}

	public void draw() // draws this point
	{
		StdDraw.point(x, y);
	}

	// draws the line segment from this point to that point
	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	// string representation
	public String toString() {
		/* DO NOT MODIFY */
		return "(" + x + ", " + y + ")";
	}

	// compare two points by y-coordinates, breaking ties by x-coordinates
	public int compareTo(Point that) {
		if (this.y < that.y)
			return -1;
		else if (this.y > that.y)
			return +1;
		else {
			if (this.x < that.x)
				return -1;
			else if (this.x > that.x)
				return +1;
			else
				return 0;
		}
	}

	// the slope between this point and that point
	public double slopeTo(Point that) {
		if (this.x == that.x && this.y == that.y)
			return Double.NEGATIVE_INFINITY;
		else if (this.x == that.x && this.y != that.y)
			return Double.POSITIVE_INFINITY;
		else if (this.y == that.y)
			return 0;
		return ((double) (that.y - this.y) / (double) (that.x - this.x));
	}

	// compare two points by slopes they make with this point
	public Comparator<Point> slopeOrder() {
		return new SlopeOrder();
	}

	private class SlopeOrder implements Comparator<Point> {
		@Override
		public int compare(Point p1, Point p2) {
			// TODO Auto-generated method stub
			if (slopeTo(p1) < slopeTo(p2))
				return -1;
			else if (slopeTo(p1) > slopeTo(p2))
				return +1;

			return 0;
		}
	}
}
