package Graph;

import java.util.LinkedList;

public class GraphMatrix {
  int V;
  int[][] adjMatrix;

  public GraphMatrix(int V) {
    this.V = V;
    adjMatrix = new int[V][V];
    for (int i = 0; i < V; i++) {
      for (int j = 0; j < V; j++) {
        adjMatrix[i][j] = 0;
      }
    }
  }

  void addEdge(int u, int v) {
    adjMatrix[u][v] = 1;
  }

  void printEdge() {
    for (int i = 0; i < this.V; i++) {
      for (int j = 0; j < this.V; j++) {
        System.out.print(adjMatrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  void printGraph() {
    for (int i = 0; i < this.V; i++) {
      System.out.print(i);
      for (int j = 0; j < this.V; j++) {
        if (adjMatrix[i][j] > 0) {
          System.out.print("->" + j);
        }
      }
      System.out.println();
    }
  }

  private void bfs(int start) {
    boolean[] visited = new boolean[this.V];
    LinkedList<Integer> queue = new LinkedList<>();
    queue.add(start);
    while (!queue.isEmpty()) {
      int v = queue.poll();
      System.out.print(v + " ");
      visited[v] = true;
      for (int j = 0; j < this.V; j++) {
        if (adjMatrix[v][j] > 0) {
          if (!visited[j]) {
            queue.add(j);
          }
        }
      }
    }
  }

  private void dfs(int start) {
    boolean[] visited = new boolean[this.V];
    dfsUtils(start, visited);
  }

  private void dfsUtils(int v, boolean[] visited) {
    visited[v] = true;
    System.out.print(v + " ");
    for (int j = 0; j < this.V; j++) {
      if (adjMatrix[v][j] > 0) {
        if (!visited[j]) {
          dfsUtils(j, visited);
        }
      }
    }
  }

  private boolean isCyclic() {
    boolean[] visited = new boolean[this.V];
    for (int v = 0; v < this.V; v++) {
      if (!visited[v]) {
        return isCyclicUtils(v, visited, -1);
      }
    }
    return false;
  }

  private boolean isCyclicUtils(int v, boolean[] visited, int parent) {
    visited[v] = true;
    for (int j = 0; j < this.V; j++) {
      if (adjMatrix[v][j] > 0) {
        if (!visited[j]) {
          return isCyclicUtils(j, visited, v);
        } else if (parent != j) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    GraphMatrix g1 = new GraphMatrix(4);
    //g1.addEdge(2, 0);
    g1.addEdge(2, 3);
    g1.addEdge(0, 1);
    g1.addEdge(0, 2);
    //g1.addEdge(1, 2);
    //g1.addEdge(3, 3);

    System.out.println("Print adjacency matrix");
    g1.printEdge();
    System.out.println("Print Graph");
    g1.printGraph();

    System.out.println("Print BFS:");
    int start = 0;
    g1.bfs(start);

    System.out.println("\nPrint DFS:");
    start = 0;
    g1.dfs(start);

    if (g1.isCyclic()) {
      System.out.println("\nThis graph contains cycle");
    } else {
      System.out.println("\nThis graph doesn't contain cycle");
    }
  }
}
