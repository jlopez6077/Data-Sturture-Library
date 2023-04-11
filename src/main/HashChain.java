package main;

import java.util.LinkedList;

public class HashChain {
	// Fields

	private LinkedList<Node> chain;

	private class Node {

		String key;

		String value;

		public Node(String key, String value) {

			this.key = key;

			this.value = value;

		}

	}

	// Constructor

	public HashChain() {

		chain = new LinkedList<Node>();

	}

	// Methods

	public boolean addNode(String key, String value) {

		if (contains(key))

			return false; // Do not add duplicate key!

		// (We could also choose to replace existing key)

		Node node = new Node(key, value);

		chain.add(node);

		return true;

	}

	public boolean contains(String key) {

		for (int i = 0; i < chain.size(); i++)

			if (chain.get(i).key.equals(key))

				return true;

		return false;

	}

	public String getValue(String key) {

		for (int i = 0; i < chain.size(); i++)

			if (chain.get(i).key.equals(key))

				return chain.get(i).value;

		return null;

	}

	public boolean removeNode(String key) {

		for (int i = 0; i < chain.size(); i++)

			if (chain.get(i).key.equals(key)) {

				chain.remove(i);

				return true;

			}

		return false;

	}

	public void clear() {

		chain.clear();

	}

	public String toString() {

		String ret = "[";

		for (int i = 0; i < chain.size(); i++)

			ret += "<" + chain.get(i).key + ", " + chain.get(i).value + ">,";

		if (ret.length() > 1) // Trim trailing comma only if we have an item in here

			ret = ret.substring(0, ret.length() - 1);

		ret += "]";

		return ret;

	}
}

class Hash {

	// Fields

	private final int M = 8; // Size of array

	private HashChain[] map;

	private int n; // How many key,value pairs in map

	// Constructor

	public Hash() {

		map = new HashChain[M];

		for (int i = 0; i < M; i++)

			map[i] = new HashChain(); // Initialize

		n = 0;

	}

	// Methods

	private int hash(String key) {

		final int R = 31;

		int h = 0;

		for (int i = 0; i < key.length(); i++)

			h = ((R * h) + key.charAt(i)) % M; // Text recommends 31 as 'R'

		return h;

	}

	public String getValue(String key) {

		int e = hash(key);

		return map[e].getValue(key);

	}

	public void put(String key, String value) {

		int e = hash(key);

		if (map[e].addNode(key, value))

			n++;

	}

	public void delete(String key) {

		int e = hash(key);

		if (map[e].removeNode(key))

			n--;

	}

	public boolean isEmpty() {

		return n == 0;

	}

	public int size() {

		return n;

	}

	public void clear() {

		for (int i = 0; i < M; i++)

			map[i].clear();

		n = 0;

	}

	public String toString() {

		String ret = "";

		for (int i = 0; i < M; i++)

			ret += map[i].toString() + "\n";

		return ret;

	}

}
