package main;

public class LinkedQueue {
	// Fields

	private Node head;

	private Node tail;

	private int n;

	private class Node {

		int item;

		Node next;

	}

	// Constructor

	public LinkedQueue(){

	head = null;

	tail = null;

	n = 0;

	}

	// Methods

	public void enqueue(int item) {

		Node oldTail = tail;

		tail = new Node();

		tail.item = item;

		tail.next = null;

		if (isEmpty())
			head = tail;

		else
			oldTail.next = tail;

		n++;

	}

	public int dequeue() {

		if (n == 0)
			return -1;

		int item = head.item;

		head = head.next;

		if (isEmpty())
			tail = null;

		n--;

		return item;

	}

	public int peek() {

		if (n == 0)
			return -1;

		return head.item;

	}

	public boolean isEmpty() {

		return head == null;

	}

	public int size() {

		return n;

	}

	public void clear() {

		head = null;

		tail = null;

		n = 0;

	}
}
