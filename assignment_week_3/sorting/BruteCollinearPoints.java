package assignment_week_3.sorting;

import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.Quick;

public class BruteCollinearPoints {

	private ArrayList<LineSegment> segment = new ArrayList<>();
	private final Point[] points;

	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		this.points = Arrays.copyOf(points, points.length);
		validatePoints(this.points);
		Quick.sort(this.points);
		for (int a = 0; a < this.points.length; a = a + 1) {
			for (int b = a + 1; b < this.points.length; b++) {
				for (int c = b + 1; c < this.points.length; c++) {
					for (int d = c + 1; d < this.points.length; d++) {
						if (this.points[a].slopeTo(this.points[b]) == this.points[a].slopeTo(this.points[c])
								&& this.points[a].slopeTo(this.points[b]) == this.points[a].slopeTo(this.points[d])) {
							segment.add(new LineSegment(this.points[a], this.points[d]));
						}
					}
				}
			}
		}

	}

	private void validatePoints(Point[] points) {
		// TODO Auto-generated method stub
		if (points == null)
			throw new NullPointerException();
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null)
				throw new NullPointerException();
			for (int j = i + 1; j < points.length; j++) {
				if (points[j] == null)
					throw new NullPointerException();
				if (points[i].compareTo(points[j]) == 0)
					throw new IllegalArgumentException("Repeated Point Found Exception.");
			}
		}
	}

	// the number of line segments
	public int numberOfSegments() {
		return segment.size();
	}

	// the line segments
	public LineSegment[] segments() {
		return segment.toArray(new LineSegment[segment.size()]);
	}
}
