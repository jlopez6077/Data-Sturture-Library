package main;

public class FixedQueue {
	// Fields

	private int[] a;

	private int head;

	private int tail;

	private int n;

	// Constructor

	public FixedQueue(){

	a = new int[256];

	head = 0;

	tail = 0;

	n = 0;

	}

	// Methods

	public void enqueue(int item) {

		if (size() == a.length)
			return; // No room left!

		if (tail >= a.length)

			tail = 0;

		a[tail] = item;

		tail++;

		n++;

	}

	public int dequeue() {

		if (isEmpty())
			return -1; // Error code

		int ret = a[head];

		head++;

		n--;

		if (head >= a.length)

			head = 0;

		return ret;

	}

	public int peek() {

		if (isEmpty())
			return -1; // Error code

		return a[head];

	}

	public boolean isEmpty() {

		return size() == 0;

	}

	public int size() {

		return n;

	}

	public void clear() {

		head = 0;

		tail = 0;

		n = 0;

	}
}
