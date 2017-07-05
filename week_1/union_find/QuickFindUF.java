package week_1.union_find;

public class QuickFindUF {
	private int[] id;

	/*
	 * initialize with i value to each id[i] N array access
	 */
	public QuickFindUF(int n) {
		id = new int[n];
		for (int i = 0; i < n; i++)
			id[i] = i;
	}

	/*
	 * check for two entry for their id, if both node has same id then they have
	 * connected path else not 2 array access
	 */
	public boolean connected(int p, int q) {
		return (id[p] == id[q]);
	}

	/*
	 * change all id value who has value same as id[p] to id[q] 
	 * at most 2N+2 array access
	 */
	public void union(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i < id.length; i++)
			if (id[i] == pid)
				id[i] = qid;
	}
}
