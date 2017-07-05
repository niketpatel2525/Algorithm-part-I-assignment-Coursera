package assignment_week_3.sorting;

import java.util.HashSet;

public class Sample {
	public static void main(String[] args) {
		HashSet<Point> a = new HashSet<>();
		Point p = new Point(1, 1);
		a.add(p);
		a.add(p);
		a.add(p);
		a.add(p);
		System.out.println(a.size());
	}
}
