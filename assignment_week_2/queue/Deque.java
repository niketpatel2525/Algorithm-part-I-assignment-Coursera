package assignment_week_2.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node first, last;
	private int size;

	private class Node {
		private Item item;
		private Node next;
		private Node prev;
	}

	public Deque() // construct an empty deque
	{
		size = 0;
		first = last = null;
	}

	public boolean isEmpty() // is the deque empty?
	{
		return size == 0;
	}

	public int size() // return the number of items on the deque
	{
		return size;
	}

	public void addFirst(Item item) // add the item to the front
	{
		if (item == null)
			throw new NullPointerException();
		Node of = first;
		first = new Node();
		first.prev = null;
		first.item = item;
		first.next = of;
		if (isEmpty())
			last = first;
		else
			of.prev = first;
		size++;
	}

	public void addLast(Item item) // add the item to the end
	{
		if (item == null)
			throw new NullPointerException();
		Node ol = last;
		last = new Node();
		last.prev = ol;
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			ol.next = last;
		size++;

	}

	public Item removeFirst() // remove and return the item from the front
	{
		if (isEmpty())
			throw new NoSuchElementException();
		Node currentFirst = first;
		first = first.next;
		size--;
		if (isEmpty())
			last = first;
		else
			first.prev = null;
		return currentFirst.item;

	}

	public Item removeLast() // remove and return the item from the end
	{
		if (isEmpty())
			throw new NoSuchElementException();
		Node currentLast = last;
		last = last.prev;
		size--;
		if (isEmpty())
			first = last;
		else
			last.next = null;
		return currentLast.item;
	}

	public Iterator<Item> iterator() // return an iterator over items in order
										// from front to end
	{
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {
		private Node current = first;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
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
			Item item = current.item;
			current = current.next;
			return item;

		}

	}

	public static void main(String[] args) // unit testing (optional)
	{

	}
}
