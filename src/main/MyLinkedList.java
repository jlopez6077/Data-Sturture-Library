package main;

import java.util.LinkedList;

public class MyLinkedList {
	// Fields

	private Node head;

	private int n;

	private class Node {

		String item;

		Node next;

	}

	// Constructor

	public MyLinkedList() {

		head = null;

		n = 0;

	}

	// Methods

	public void add(int index, String item) {

		// Create node to add

		Node X = new Node();

		X.item = item;

		X.next = null;

		// Check for strange use cases...

		if (head == null) {

			if (index != 0)
				return; // Not a valid index relative to current list

			head = X; // Set our new node as head

			n++;

			return;

		} else if (head != null && index == 0) {

			X.next = head;

			head = X;

			n++;

			return;

		}

		// Otherwise, traverse the linked list for index position

		if (index > n)
			return; // Cannot insert at an index larger than n

		Node current = head;

		Node previous = null;

		int i = 0;

		while (i < index) {

			previous = current;

			current = current.next;

			if (current == null)
				break;

			i++;

		}

		X.next = current;

		previous.next = X;

		n++;

	}

	public boolean contains(String item) {

		for (int i = 0; i < n; i++) {

			if (get(i).equals(item))

				return true;

		}

		return false; // For now (default)

	}

	public String get(int index) {

		if (index > n - 1)
			return "Invalid Index!";

		if (index == 0)
			return head.item;

		Node cur = head;

		int i = 0;

		// Traverse to find correct node to "get" item from...

		while (i < index) {

			cur = cur.next;

			i++;

		}

		return cur.item;

	}

	public String peek() {

		if (head != null)
			return head.item;

		return "Null"; // Safe return!

	}

	public void remove(int index) {

		// Strange cases first...

		if (index > n - 1)
			return; // Cannot remove from index that doesn't exist!

		if (head != null && index == 0) {

			head = head.next;

			n--;

			return;

		}

		// Otherwise, traverse the linked list for the index of node to remove...

		Node current = head;

		Node previous = null;

		int i = 0;

		while (i < index) {

			previous = current;

			current = current.next;

			if (current == null)
				break;

			i++;

		}

		previous.next = current.next;

		n--;

	}

	public int size() {

		return n;

	}

	public void clear() {

		head = null;

		n = 0;

	}
}

class MyLinkedListTest {
	public static void main(String[] args) {
		LinkedList<Integer> lst = new LinkedList<Integer>(); // Create Linked List Object

		for (int i = 0; i < 10; i++) {

			lst.add(0, i); // Add 0 - 9 to list

		}

		lst.remove(0); // Remove head of linked list

		for (int i = 0; i < lst.size(); i++)

			System.out.print(lst.get(i) + " "); // Print items in list from head to tail

		System.out.println("\nList contains number 9: " + lst.contains(9));

		lst.clear(); // Remove everything from the list

		System.out.println(lst.peek()); // This should be null
	}

	public static void pn(Object e) {
		System.out.println(e);
	}
}