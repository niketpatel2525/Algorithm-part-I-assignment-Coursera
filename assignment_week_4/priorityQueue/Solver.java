package assignment_week_4.priorityQueue;

import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	private MinPQ<Node> orig, twin;
	private ArrayList<Board> result;
	private boolean solved;

	private class Node implements Comparable<Node> {
		private final Board board;
		private final int moves;
		private final Node parent;

		Node(Board b, int m, Node p) {
			this.board = b;
			this.moves = m;
			this.parent = p;
		}

		@Override
		public int compareTo(Node that) {
			// TODO Auto-generated method stub
			return (this.board.manhattan() + this.moves) - (that.board.manhattan() + that.moves);
		}

	}

	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		if (initial == null)
			throw new NullPointerException();
		orig = new MinPQ<>();
		twin = new MinPQ<>();
		orig.insert(new Node(initial, 0, null));
		twin.insert(new Node(initial.twin(), 0, null));
		while (!solved) {
			goForSolution(orig);
			goForSolution(twin);
		}
	}

	private void goForSolution(MinPQ<Node> hq) {
		// TODO Auto-generated method stub
		Node current = hq.delMin();
		if (current.board.isGoal()) {
			if (hq == orig) {
				result = new ArrayList<>();
				while (current != null) {
					result.add(0, current.board);
					current = current.parent;
				}
			}
			solved = true;
			return;
		}

		for (Board neighbour : current.board.neighbors()) {
			if (current.parent == null || !neighbour.equals(current.parent.board)) {
				hq.insert(new Node(neighbour, current.moves + 1, current));
			}
		}
	}

	// is the initial board solvable?
	public boolean isSolvable() {
		return result != null;
	}

	// min number of moves to solve initial board; -1 if unsolvable
	public int moves() {
		if (!isSolvable())
			return -1;
		else
			return result.size() - 1;
	}

	// sequence of boards in a shortest solution; null if unsolvable
	public Iterable<Board> solution() {
		return result;
	}

	// solve a slider puzzle (given below)
	public static void main(String[] args) {
		// create initial board from file
		In in = new In(args[0]);
		int n = in.readInt();
		int[][] blocks = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				blocks[i][j] = in.readInt();
		// int blocks[][] = {{0,1,3},{4,2,5},{7,8,6}};
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}
}
