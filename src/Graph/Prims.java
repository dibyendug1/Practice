package Graph;

import java.io.PrintStream;

public class Prims {
  static final int V = 5;
  static final int INF = Integer.MAX_VALUE;

  public static void main(String[] args) {
    int graph[][] = {
        { 0, 2, 0, 6, 0 },
        { 2, 0, 3, 8, 5 },
        { 0, 3, 0, 0, 7 },
        { 6, 8, 0, 0, 9 },
        { 0, 5, 7, 9, 0 } };

    int start = 0;
    System.out.println("Prims minimum spanning tree:");
    primMST(graph, start);
  }

  private static void primMST(int[][] graph, int start) {
    int[] dist = new int[V];
    boolean[] visited = new boolean[V];
    int[] parent = new int[V];

    for (int i = 0; i < V; i++) {
      parent[i] = -1;
      dist[i] = INF;
    }
    dist[start] = 0;
    for (int node = 0; node < V - 1; node++) {
      int u = minDist(dist, visited);
      visited[u] = true;

      for (int v = 0; v < V; v++) {
        if (!visited[v] && dist[u] != INF && graph[u][v] != 0
            && graph[u][v] < dist[v]) {
          parent[v] = u;
          dist[v] = graph[u][v];
        }
      }
    }
    printMST(parent, dist);
  }

  private static void printMST(int[] parent, int[] dist) {
    for (int i = 0; i < V; i++) {
      if (parent[i] == -1) {
        continue;
      }
      System.out.println(parent[i] + "->" + i + " dist: " + dist[i]);
    }
  }

  private static int minDist(int[] dist, boolean[] visited) {
    int min = INF;
    int index = 0;
    for (int i = 0; i < V; i++) {
      if(!visited[i] && min > dist[i]){
        min = dist[i];
        index = i;
      }
    }

    return index;
  }

}
