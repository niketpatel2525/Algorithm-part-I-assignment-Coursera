package assignment_week_5.kd_tree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	private Node root;
	private int size;
	private RectHV board;

	public KdTree() {
		root = null;
		size = 0;
		board = new RectHV(0.0, 0.0, 1.0, 1.0);
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public int size() {
		return size;
	}

	public void insert(Point2D p) {
		root = put(root, p, p, false);
	}

	private Node put(Node node, Point2D key, Point2D val, boolean isHorizontal) {
		// TODO Auto-generated method stub
		if (node == null) {
			size++;
			return new Node(key, val, null, null, isHorizontal);
		}
		if (node.key.x() == key.x() && node.key.y() == key.y()) {
			node.val = val;
		} else if ((!node.isHorizontal && node.key.x() >= key.x()) || (node.isHorizontal && node.key.y() >= key.y())) {
			node.left = put(node.left, key, val, !isHorizontal);
		} else {
			node.right = put(node.right, key, val, !isHorizontal);
		}

		return node;
	}

	public boolean contains(Point2D p) {
		Node node = root;
		while (node != null) {
			if (node.key.x() == p.x() && node.key.y() == p.y())
				return true;
			else if ((!node.isHorizontal && node.key.x() >= p.x()) || (node.isHorizontal && node.key.y() >= p.y())) {
				node = node.left;
			} else
				node = node.right;
		}
		return false;
	}

	public void draw() {
		StdDraw.setScale(0, 1);
		draw(root, board);
	}

	private void draw(Node node, RectHV rect) {
		// TODO Auto-generated method stub
		if (node != null) {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setPenRadius(0.02);
			new Point2D(node.key.x(), node.key.y()).draw();
			StdDraw.setPenRadius();

			if (!node.isHorizontal) {
				StdDraw.setPenColor(StdDraw.RED);
				new Point2D(node.key.x(), rect.ymin()).drawTo(new Point2D(node.key.x(), rect.ymax()));
			} else {
				StdDraw.setPenColor(StdDraw.BLUE);
				new Point2D(rect.xmin(), node.key.y()).drawTo(new Point2D(rect.xmax(), node.key.y()));
			}
			draw(node.left, leftRect(rect, node));
			draw(node.right, rightRect(rect, node));
		}
	}

	private RectHV leftRect(RectHV rect, Node node) {
		// TODO Auto-generated method stub
		if (node.isHorizontal)
			return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.key.y());
		return new RectHV(rect.xmin(), rect.ymin(), node.key.x(), rect.ymax());
	}

	private RectHV rightRect(RectHV rect, Node node) {
		// TODO Auto-generated method stub
		if (node.isHorizontal)
			return new RectHV(rect.xmin(), node.key.y(), rect.xmax(), rect.ymax());
		return new RectHV(node.key.x(), rect.ymin(), rect.xmax(), rect.ymax());
	}

	public Iterable<Point2D> range(RectHV rect) {
		Stack<Point2D> stack = new Stack<>();
		get(root, board, rect, stack);
		return stack;
	}

	private void get(Node node, RectHV nodeRect, RectHV rect, Stack<Point2D> stack) {
		// TODO Auto-generated method stub
		if (node != null) {
			if (nodeRect.intersects(rect)) {
				if (rect.contains(node.val))
					stack.push(node.val);
				get(node.left, leftRect(nodeRect, node), rect, stack);
				get(node.right, rightRect(nodeRect, node), rect, stack);
			}
		}
	}

	public Point2D nearest(Point2D p) {
		return nearestNeighbor(root, board, p, null);
	}

	private Point2D nearestNeighbor(Node node, RectHV rect, Point2D p, Point2D nearPoint) {
		// TODO Auto-generated method stub

		Point2D nearestPoint = nearPoint;
		if (node != null) {
			if (nearestPoint == null || nearestPoint.distanceSquaredTo(p) > rect.distanceSquaredTo(p)) {
				if (nearestPoint == null) {
					nearestPoint = node.key;
				} else {
					if (node.key.distanceSquaredTo(p) < nearestPoint.distanceSquaredTo(p))
						nearestPoint = node.key;
				}
			}
			if (!node.isHorizontal) {
				RectHV leftRect = new RectHV(rect.xmin(), rect.ymin(), node.key.x(), rect.ymax());
				RectHV rightRect = new RectHV(node.key.x(), rect.ymin(), rect.xmax(), rect.ymax());
				if (p.x() <= node.key.x()) {
					nearestPoint = nearestNeighbor(node.left, leftRect, p, nearestPoint);
					nearestPoint = nearestNeighbor(node.right, rightRect, p, nearestPoint);
				} else {
					nearestPoint = nearestNeighbor(node.right, rightRect, p, nearestPoint);
					nearestPoint = nearestNeighbor(node.left, leftRect, p, nearestPoint);
				}

			} else {
				RectHV leftRect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.key.y());
				RectHV rightRect = new RectHV(rect.xmin(), node.key.y(), rect.xmax(), rect.ymax());
				if (p.y() <= node.key.y()) {
					nearestPoint = nearestNeighbor(node.left, leftRect, p, nearestPoint);
					nearestPoint = nearestNeighbor(node.right, rightRect, p, nearestPoint);
				} else {
					nearestPoint = nearestNeighbor(node.right, rightRect, p, nearestPoint);
					nearestPoint = nearestNeighbor(node.left, leftRect, p, nearestPoint);
				}
			}
		}

		return nearestPoint;
	}

	private class Node {
		private Point2D key;
		private Point2D val;
		private Node left, right;
		private boolean isHorizontal = false;

		public Node(Point2D key, Point2D val, Node left, Node right, boolean isHorizontal) {
			this.key = key;
			this.val = val;
			this.left = left;
			this.right = right;
			this.isHorizontal = isHorizontal;
		}
	}
}
