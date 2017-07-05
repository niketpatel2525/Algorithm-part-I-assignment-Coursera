package assignment_week_2.queue;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue<String> rq = new RandomizedQueue<>();
		int k = Integer.parseInt(args[0]);
		while (!StdIn.isEmpty())
			rq.enqueue(StdIn.readString());
		for (int i = 0; i < k; i++)
			StdOut.print(rq.dequeue());
	}

}
