package main;

public class DynamicStack {
	// Fields

	private int[] a;

	private int cursor;

	// Constructor

	public DynamicStack(){

	a = new int[256]; // Start at a power of two

	cursor = 0;

	}

	// Methods

	private void resize() {

		int n = a.length * 2;

		int[] b = new int[n];

		for (int i = 0; i < a.length; i++)

			b[i] = a[i];

		a = b;

	}

	public void push(int item) {

		if (cursor >= a.length) // Resize here...

			resize();

		a[cursor] = item;

		cursor++;

	}

	public int pop() {

		int ret = -1; // Set to error code

		if (cursor > 0) {

			cursor--;

			ret = a[cursor];

		}

		return ret;

	}

	public int peek() {

		int ret = -1;

		if (cursor > 0) {

			ret = a[cursor - 1];

		}

		return ret;

	}

	public boolean isEmpty() {

		return cursor == 0;

	}

	public int size() {

		return cursor;

	}

	public void clear() {

		cursor = 0; // Be lazy and save cycles

	}
}
