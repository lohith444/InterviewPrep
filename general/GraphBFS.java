import java.io.*;
import java.util.*;

/* Write a function to implement Breadth first traversal of a given graph.

O(V+E) time complexity.
V -> Number of vertices.
E -> Number of edges in the graph.

Graph:
Vetices: 0, 1, 2, 3
Edges: 0->1, 0->2, 1->2, 2->0, 2->3, 3->3

   (0)--->(1)
   |^   /     
   ||  /
   v|\//   v)
   (2)--->(3)
*/

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static class Graph {
    private int V; // no. of vertices
    private LinkedList<Integer> adj[];
    Graph(int v) {
      V = v;
      adj = new LinkedList[v];
      for (int i=0; i<v; i++) {
        adj[i] = new LinkedList();
      }
    }
    
    public void addEdge(int v, int w) {
      adj[v].add(w);
    }
    
    public void BFS(int s) {
      boolean visited[] = new boolean[V];
      LinkedList<Integer> queue = new LinkedList<Integer>();
      queue.add(s);
      visited[s] = true;
      
      while(!queue.isEmpty()) {
        s = queue.poll();
        System.out.print(s + " ");
        
        Iterator<Integer> i = adj[s].listIterator();
        while(i.hasNext()) {
          int n = i.next();
          if (!visited[n]) {
            visited[n] = true;
            queue.add(n);
          }
        }
      }
    }
  }
  
  public static void main(String[] args) {
    Graph g = new Graph(4);
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
    g.addEdge(2, 0);
    g.addEdge(2, 3);
    g.addEdge(3, 3);
    
    int i = 2;
    System.out.println("Following is BFS starting from vertex: " + i);
    g.BFS(i);
  }
}
