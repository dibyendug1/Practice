package Graph;

import java.util.Iterator;
import java.util.LinkedList;

public class Dijkstra {
  final static int INF = Integer.MAX_VALUE;

  private static void dijkstraUtils(int[][] g1, int start) {
    boolean[] visited = new boolean[g1.length];
    int[] dist = new int[g1.length];
    for (int i = 0; i < g1.length; i++) {
      dist[i] = INF;
    }
    dist[start] = 0;
    for (int node = 0; node < g1.length - 1; node++) {
      int u = misDist(dist, visited);
      visited[u] = true;
      for (int v = 0; v < g1.length; v++) {
        if (!visited[v] && dist[u] != INF && g1[u][v] != 0
            && (dist[u] + g1[u][v]) < dist[v]) {
          dist[v] = dist[u] + g1[u][v];
        }
      }
    }

    for (int j = 0; j < g1.length; j++) {
      System.out.println(j + " dist " + dist[j]);
    }
  }

  private static int misDist(int[] dist, boolean[] visited) {
    int min = INF;
    int index = -1;
    for (int i = 0; i < dist.length; i++) {
      if (!visited[i] && min > dist[i]) {
        min = dist[i];
        index = i;
      }
    }
    return index;
  }

  public static void main(String[] args) {
    int[][] g1 =
        { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
            { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
            { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
            { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
            { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
            { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
            { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
            { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
            { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

    dijkstraUtils(g1, 0);
    System.out.println("\nFind shortest path:");
    shortestPath(g1, 0);
  }

  private static void shortestPath(int[][] g1, int start) {
    final int V = g1.length;
    int[] dist = new int[V];
    boolean[] visited = new boolean[V];
    LinkedList<Integer>[] path = new LinkedList[V];
    int[] patharr = new int[V];
    for (int i = 0; i < V; i++) {
      path[i] = new LinkedList<Integer>();
      dist[i] = INF;
      patharr[i] = -1;
    }

    dist[start] = 0;
    path[start].add(start);
    patharr[start] = 0;

    for (int node = 0; node < V - 1; node++) {
      int u = misDist(dist, visited);
      visited[u] = true;

      for (int v = 0; v < V; v++) {
        if (!visited[v] && dist[u] != INF && g1[u][v] != 0
            && (dist[u] + g1[u][v]) < dist[v]) {
          dist[v] = dist[u] + g1[u][v];
          createPath(path, u, v);
          patharr[v] = u;
        }
      }

    }

    for (int i = 0; i < V; i++) {
      System.out.printf("%d->%2d dist %3d path:", 0, i, dist[i]);
      Iterator<Integer> itr = path[i].iterator();
      int count = 0;
      while (itr.hasNext()) {
        count += 1;
        if (count == path[i].size()) {
          System.out.println(itr.next());
        } else {
          System.out.print(itr.next() + "->");
        }
      }
    }
  }

  private static void createPath(LinkedList<Integer>[] path, int u, int v) {
    Iterator<Integer> itr = path[u].iterator();
    path[v].clear();
    while (itr.hasNext()){
      path[v].add(itr.next());
    }
    path[v].add(v);
  }
}
