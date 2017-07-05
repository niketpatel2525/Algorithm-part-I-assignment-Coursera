package assignment_week_2.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int size;
	private Item[] item;

	@SuppressWarnings("unchecked")
	public RandomizedQueue() // construct an empty randomized queue
	{
		size = 0;
		item = (Item[]) new Object[1];
	}

	private void resize(int capacity) {
		@SuppressWarnings("unchecked")
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < size; i++)
			copy[i] = item[i];
		item = copy;
	}

	public boolean isEmpty() // is the queue empty?
	{
		return size == 0;
	}

	public int size() // return the number of items on the queue
	{
		return size;
	}

	public void enqueue(Item item) // add the item
	{
		if (item == null)
			throw new NullPointerException();
		this.item[size++] = item;
		if (size == this.item.length)
			resize(2 * this.item.length);
	}

	public Item dequeue() // remove and return a random item
	{
		if (isEmpty())
			throw new NoSuchElementException();
		int randomVal = StdRandom.uniform(this.size);
		Item i = this.item[randomVal];
		this.item[randomVal] = this.item[size - 1];
		this.item[--size] = null;
		if (size > 0 && size == this.item.length / 4)
			resize(this.item.length / 2);
		return i;
	}

	public Item sample() // return (but do not remove) a random item
	{
		if (isEmpty())
			throw new NoSuchElementException();
		return this.item[StdRandom.uniform(size)];
	}

	public Iterator<Item> iterator() // return an independent iterator over
										// items in random order
	{
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {
		private int[] order;
		private int ptr = size;

		public RandomizedQueueIterator() {
			// TODO Auto-generated constructor stub
			order = new int[size];
			for (int i = 0; i < size; i++)
				order[i] = i;
			StdRandom.shuffle(order);
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return ptr > 0;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			if (!hasNext())
				throw new NoSuchElementException();
			return item[order[--ptr]];
		}

	}

	public static void main(String[] args) {

	}
}