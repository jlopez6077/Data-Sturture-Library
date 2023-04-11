package main;

public class FixedStack {

	// Fields

	private int[] a;

	private int cursor;

	// Constructor

	public FixedStack() {

		a = new int[256];

		cursor = 0;

	}

	// Methods

	public void push(int item) {

		if (cursor >= a.length) // Prevent buffer overrun

			return;

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
