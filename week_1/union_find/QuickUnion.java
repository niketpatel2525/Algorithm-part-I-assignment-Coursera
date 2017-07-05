package week_1.union_find;

public class QuickUnion {
	private int[] id;

	/*
	 * initialize id array with its node number
	 * N array access
	 */
	public QuickUnion(int n) {
		id = new int[n];
		for (int i = 0; i < n; i++)
			id[i] = i;
	}

	/*
	 * this method return root node value of each element
	 * depth of i array access
	 */
	private int root(int i) {
		while (i != id[i])
			i = id[i];
		return i;
	}

	/*
	 *  check is there path from p to q  by checking their root node value.
	 *  if root is same, there is connection between p and q else not.
	 *  
	 *  depth of p and q access
	 */
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	/*
	 *  attach tree containing p to the root of q.
	 *  
	 *  depth of p and q array access.
	 */
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}
}
