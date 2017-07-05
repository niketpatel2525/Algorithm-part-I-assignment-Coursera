package assignment_week_5.kd_tree;

import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;

public class PointSET {
	private TreeSet<Point2D> set;

	// construct an empty set of points
	public PointSET() {
		set = new TreeSet<>();
	}

	// is the set empty?
	public boolean isEmpty() {
		return set.isEmpty();
	}

	// number of points in the set
	public int size() {
		return set.size();
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException();
		set.add(p);
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException();
		return set.contains(p);
	}

	// draw all points to standard draw
	public void draw() {
		for (Point2D p : set)
			p.draw();
	}

	// all points that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect) {
		Stack<Point2D> stack = new Stack<>();
		for (Point2D p : set) {
			if (rect.contains(p))
				stack.push(p);
		}
		return stack;
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if (set.isEmpty())
			return null;
		Point2D neighbor = set.first();
		double minDist = p.distanceSquaredTo(neighbor);
		for (Point2D point : set) {
			if (p.distanceSquaredTo(point) <= minDist) {
				neighbor = point;
				minDist = p.distanceSquaredTo(neighbor);
			}
		}
		return neighbor;
	}

	// unit testing of the methods (optional)
	public static void main(String[] args) {

	}
}
