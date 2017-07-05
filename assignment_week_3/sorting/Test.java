package assignment_week_3.sorting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Test {
	public static void main(String[] args) {
		Point[] points = new Point[6];
		points[0] = new Point(19000, 10000);
		points[1] = new Point(18000, 10000);
		points[2] = new Point(32000, 10000);
		points[3] = new Point(21000, 10000);
		points[4] = new Point(1234, 5678);
		points[5] = new Point(14000, 10000);
		System.out.println("Start");
		// BruteCollinearPoints b = new BruteCollinearPoints(points);
		// for (LineSegment segment : b.segments()) {
		// StdOut.println(segment);
		// segment.draw();
		// }

		FastCollinearPoints f = new FastCollinearPoints(points);
		for (LineSegment segment : f.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		
		System.out.println("End");
	}
}
