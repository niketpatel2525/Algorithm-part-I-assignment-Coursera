package assignment_week_4.priorityQueue;

import java.util.ArrayList;

public class Board {
	private final int[][] blocks;
	private final int N, hamming, manhattan;
	private int i, j;

	/*
	 * construct a board from an n-by-n array of blocks (where blocks[i][j] =
	 * block in row i, column j)
	 * 
	 */
	public Board(int[][] blocks) {
		if (blocks == null)
			throw new NullPointerException();
		N = blocks.length;
		this.blocks = new int[N][N];
		int ham = 0, man = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				this.blocks[i][j] = blocks[i][j];
				int inPlace = i * N + j + 1;
				int inBlock = blocks[i][j];
				if (inBlock == 0) {
					this.i = i;
					this.j = j;
				} else if (inBlock != inPlace) {
					ham++;
					man += manhattanDistance(inBlock, i, j);
				}
			}
		}
		hamming = ham;
		manhattan = man;
	}

	private int manhattanDistance(int n, int i, int j) {
		// TODO Auto-generated method stub
		int rDist = (n - 1) / dimension();
		int cDist = (n - 1) % dimension();
		return Math.abs(i - rDist) + Math.abs(j - cDist);
	}

	// board dimension n
	public int dimension() {
		return N;
	}

	// number of blocks out of place
	public int hamming() {
		return hamming;
	}

	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		return manhattan;
	}

	// is this board the goal board?
	public boolean isGoal() {
		return manhattan == 0;
	}

	/*
	 * a board that is obtained by exchanging any pair of blocks
	 */
	public Board twin() {
		int rSwap = Math.abs(this.i - 1);
		int cSwap = Math.abs(this.j - 1);
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = blocks[i][j];
			}
		}
		int tVal = temp[rSwap][cSwap];
		temp[rSwap][cSwap] = temp[rSwap][this.j];
		temp[rSwap][this.j] = tVal;
		return new Board(temp);
	}

	// does this board equal y?
	public boolean equals(Object y) {
		if (y == null)
			return false;
		if (y == this)
			return true;
		if (y.getClass() != this.getClass())
			return false;
		else {
			Board that = (Board) y;
			if (this.N != that.N || this.i != that.i || this.j != that.j || this.hamming != that.hamming
					|| this.manhattan != that.manhattan) {
				return false;
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (this.blocks[i][j] != that.blocks[i][j])
						return false;
				}
			}
		}
		return true;
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		ArrayList<Board> neighbors = new ArrayList<>();
		int[][] temp = new int[N][N];
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
				temp[i][j] = blocks[i][j];
			}
		}
		if (i - 1 >= 0) {
			exchange(temp, i - 1, j); // exchange
			neighbors.add(new Board(temp));
			int t = temp[i][j];
			temp[i - 1][j] = t;
			temp[i][j] = 0;
		}

		if (i + 1 < N) {
			exchange(temp, i + 1, j);
			neighbors.add(new Board(temp));
			int t = temp[i][j];
			temp[i + 1][j] = t;
			temp[i][j] = 0;
		}
		if (j - 1 >= 0) {
			exchange(temp, i, j - 1);
			neighbors.add(new Board(temp));

			int t = temp[i][j];
			temp[i][j - 1] = t;
			temp[i][j] = 0;
		}
		if (j + 1 < N) {
			exchange(temp, i, j + 1);
			neighbors.add(new Board(temp));
			int t = temp[i][j];
			temp[i][j + 1] = t;
			temp[i][j] = 0;

		}

		return neighbors;
	}
	/*
	 * string representation of this board (in the output format specified
	 * below)
	 */

	private void exchange(int[][] temp, int r, int c) {
		// TODO Auto-generated method stub
		int t = temp[r][c];
		temp[r][c] = 0;
		temp[this.i][this.j] = t;

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("" + N);
		for (int i = 0; i < dimension(); i++) {
			sb.append("\n");
			for (int j = 0; j < dimension(); j++) {
				sb.append(" " + blocks[i][j] + " ");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("\n");
		return sb.toString();
	}

	// unit tests (not graded)
	public static void main(String[] args) {

	}
}
