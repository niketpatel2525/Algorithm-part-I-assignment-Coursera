package assignment_week_3.sorting;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
	private final LineSegment[] segments;
	private final Point[] points;

	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) {
		validatePoints(points);
		this.points = Arrays.copyOf(points, points.length);
		Arrays.sort(this.points);
		Point[] cPoints = Arrays.copyOf(this.points, this.points.length);
		ArrayList<LineSegment> lp = new ArrayList<>();

		for (int i = 0; i < cPoints.length; i++) {
			Point startPoint = this.points[i];
			Arrays.sort(cPoints);
			Arrays.sort(cPoints, startPoint.slopeOrder());

			int min = 0;
			while (min < cPoints.length && startPoint.slopeTo(cPoints[min]) == Double.NEGATIVE_INFINITY)
				min++;
			if (min != 1)
				throw new IllegalArgumentException();// check duplicate points
			int max = min;
			while (min < cPoints.length) {
				while (max < cPoints.length && startPoint.slopeTo(cPoints[max]) == startPoint.slopeTo(cPoints[min]))
					max++;
				if (max - min >= 3) {
					Point pMin = cPoints[min].compareTo(startPoint) < 0 ? cPoints[min] : startPoint;
					Point pMax = cPoints[max - 1].compareTo(startPoint) > 0 ? cPoints[max - 1] : startPoint;
					if (startPoint == pMin)
						lp.add(new LineSegment(pMin, pMax));
				}
				min = max;
			}
		}
		segments = lp.toArray(new LineSegment[lp.size()]);

	}

	// the number of line segments
	public int numberOfSegments() {
		return segments.length;
	}

	// the line segment
	public LineSegment[] segments() {
		return segments;
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
}
