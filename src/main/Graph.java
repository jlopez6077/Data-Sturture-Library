//Juan Lopez
//CSC 130 - 05 

package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	//Fields
	private int[][] g; //Use Adjacency Matrix
	private int v; //Number of verts
	private int e; //Number of edges
	private class Node{
		int item;
		Node next;
	}
	
	//Constructor
	public Graph(int v) {
		g = new int[v][v];
		this.v = v;
		e = 0;
	}
	
	//Methods
	public int V() {
		return v;
	}
	
	public int E() {
		return e;
	}
	
	public void addEdge(int v1, int v2) {
		g[v1][v2] = 1;
		g[v2][v1] = 1;
		e++;
	}
	
	public boolean isAdjacent(int v1, int v2) { //changed for weighted
		return g[v1][v2] != 0;
	}
	
	public boolean isConnected(int v1, int v2) {
		int[] bfs = BFStoArray(v1);
		for(int i = 0; i < bfs.length; i++)
			if(bfs[i] == v2)
				return true;
		return false;
	}
	
	public int[] adj(int v) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i = 0; i<this.v; i++) {
			if(g[v][i] == 1)
				al.add(i);
		}
		int[] ret = new int[al.size()];
		for(int i = 0; i <ret.length;i++)
			ret[i] = al.get(i);
		return ret;
	}
	
	public String adjString(int v) {
		int[] a = adj(v);
		String ret = "Verts adjacent to "+v+"; ";
		for(int i = 0; i < a.length; i++) {
			ret += a[i] + " ";
		}
		return ret;
	}
	public void AdjMatrix() {
		System.out.print("\t");
		for(int i = 0; i<v; i++) {
			System.out.print(i+"\t");
		}
		System.out.println("\n");
		for(int i = 0; i<v; i++) {
			System.out.print(i+"\t");
			for(int j = 0; j<v; j++) {
				System.out.print(g[i][j] + "\t");
			}
			System.out.print("\n");
		}
	}
	
	//Helper function that can be used later by isConnected as well
	private int[] BFStoArray(int v) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		boolean[] visited = new boolean[this.v];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(v);
		visited[v] = true;
		int visit;
		while(!q.isEmpty()) {
			visit = q.remove();
			al.add(visit);
			for(int i = 0; i < this.v; i++) {
				if(isAdjacent(visit, i) && !visited[i]) {
					q.add(i);
					visited[i] = true;
				}
			}
		}
		int[] ret = new int[al.size()];
		for(int i = 0; i < ret.length; i++)
			ret[i] = al.get(i);
		return ret;
	}
	
	public String BFS(int v) {
		String ret = "BFS for Vert #"+v+": ";
		int[] bfs = BFStoArray(v);
		for(int i = 0; i < bfs.length; i++) {
			ret += bfs[i] +" ";
		}
		return ret;
	}
	
	//Initiator (wrapper) method for recursive call
	public String DFS(int v) {							
		boolean[] visited = new boolean[this.v];
		String ret = DFS(v,visited,"DFS for Vert #"+v+": ");
		return ret;
	}
	
	public String DFS(int v, boolean[] visited, String str) {
		str += v + " ";
		visited[v] = true;
		for(int i = 0; i < this.v; i++) {
			if(isAdjacent(v,i) && !visited[i]) {
				str = DFS(i, visited, str);
			}
		}
		return str;
	}
	
	
	//Extra Credit
	public void addWeightedEdge(int v1, int v2, int weight) {
			if(weight<1)	return;
			g[v1][v2] = weight;
			g[v2][v1] = weight;
			e++;
	}
	public String shortestPath(int v1, int v2) {
		if( v1<0 || v1>(v-1) || v2<0 || v2>(v-1))
			return "Path does not exist";
		int[] a = shortestPathList(v1, v2);
		if(a[0] == Integer.MAX_VALUE)
			return "Shortest Path is not possible because "+v1+" and "+v2+" are not connected.";
		
		String ret = "The shortest path from "+v1+" to "+v2+" is\n"+a[1];
		for(int i = 2; i < a.length; i++) {
			ret+= " -> "+a[i];
		}
		ret+="\nTotal edge distance: "+a[0];
		return ret;
	}
	//Helper function
	private int[] shortestPathList(int v1, int v2) {
		Node[] x = new Node[v];
		int f;
		Queue<Integer> settled = new LinkedList<Integer>();
		Queue<Integer> unsettled = new LinkedList<Integer>();
		for(int i = 0; i < x.length; i++) {
			Node fill = new Node();
			fill.item = Integer.MAX_VALUE;
			x[i] = fill;
			Node temp = new Node();
			temp.item = i;
			x[i].next = temp;
		}
		x[v1].item = 0;
		
		unsettled.add(v1);
		while(!unsettled.isEmpty()) {
			f=unsettled.remove();
			for(int i = 0; i < g.length; i++) {
				//if(!settled.contains(i) && g[f][i]!=0 && g[f][i]+x[f].item<x[i].item)
				if(g[f][i]!=0 && g[f][i]+x[f].item<x[i].item){
					x[i].item = g[f][i]+x[f].item;
					Node temp = new Node();
					Node last;
					Node curr = x[f].next;
					temp.item = curr.item;
					Node tempLast = temp;
					while(curr.next != null) { 
						curr = curr.next;
						Node n = new Node();
						n.item = curr.item;
						tempLast.next = n;
						tempLast = tempLast.next;
					}
					Node n = new Node();
					n.item = i;
					last = temp;
					while(last.next != null) {
						last = last.next;
					}
					last.next = n;
					x[i].next = temp;
					unsettled.add(i);
				}
			}
			settled.add(f);
		}
		settled.clear();
		Node curr = x[v2]; 
		while(curr != null) {
			settled.add(curr.item);
			curr = curr.next;
		}
		int[] SP = new int[settled.size()];
		int count = 0;
		while(!settled.isEmpty()) {
			SP[count] = settled.remove();
			count++;
		}
		return SP;
	}
}

class GraphTest{
	public static void main(String[] args) {
		//Graph g = new Graph(7); //create new graph with 7 vertices
		/*g.addWeightedEdge(1,2,10);
		g.addWeightedEdge(1,3,5);
		g.addWeightedEdge(2,5,5);
		g.addWeightedEdge(3,5,15);
		g.addWeightedEdge(5,4,4);
		g.addWeightedEdge(5,6,2);
	
		//g.AdjMatrix();
		p(g.shortestPath(1, 6));*/
		int[] a = {6, 0, 5, 1, 4, 2, 3};

		Graph g = new Graph(a.length);

		for(int i = 0; i < a.length-1; i++){

		       g.addEdge(a[i], a[i+1]);

		}

		System.out.println(g.DFS(a[3]));
		
	}
	public static <E> void p(E item){

		System.out.println(item);

		}
}
