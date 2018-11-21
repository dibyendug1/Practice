package Graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Graph {
  int V;
  LinkedList<Integer> adjMatrix[];

  public Graph(int V) {
    this.V = V;
    adjMatrix = new LinkedList[V];
    for (int i = 0; i < V; i++) {
      adjMatrix[i] = new LinkedList<Integer>();
    }
  }

  void addEdge(int u, int v) {
    adjMatrix[u].add(v);
  }

  void printGraph() {
    for (int i = 0; i < this.V; i++) {
      System.out.println("edge from node " + i);
      System.out.print(i);
      for (Integer v : adjMatrix[i]) {
        System.out.print("->" + v);
      }
      System.out.println();
    }
  }

  private void bfs(int start) {
    LinkedList<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[this.V];
    queue.add(start);
    while (!queue.isEmpty()) {
      int v = queue.poll();
      visited[v] = true;
      System.out.print(v + " ");
      for (Integer n : adjMatrix[v]) {
        if (!visited[n]) {
          queue.add(n);
        }
      }
    }
  }

  private void dfs(int start) {
    LinkedList<Integer> stack = new LinkedList<>();
    boolean[] visited = new boolean[this.V];
    stack.push(start);
    while (!stack.isEmpty()) {
      int v = stack.peek();
      stack.pop();
      if (!visited[v]) {
        System.out.print(v + " ");
      }
      visited[v] = true;
      for (int n : adjMatrix[v]) {
        if (!visited[n]) {
          stack.push(n);
        }
      }
    }
  }

  private void dfsRecursion(int start) {
    boolean[] visited = new boolean[this.V];
    dfsUtils(start, visited);
  }

  private void dfsUtils(int v, boolean[] visited) {
    visited[v] = true;
    System.out.print(v + " ");

    Iterator<Integer> itr = adjMatrix[v].iterator();
    while (itr.hasNext()) {
      int n = itr.next();
      if (!visited[n]) {
        dfsUtils(n, visited);
      }
    }
  }

  private boolean isCycilc() {
    boolean[] visited = new boolean[this.V];
    LinkedList<Integer> stack = new LinkedList<>();

    for (int v = 0; v < this.V; v++) {
      //stack.clear();
      if (!visited[v]) {
        if(isCyclicUtils(v, visited, stack)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean isCyclicUtils(int v, boolean[] visited,
      LinkedList<Integer> stack) {
    visited[v] = true;
    stack.push(v);

    Iterator<Integer> itr = adjMatrix[v].iterator();
    while (itr.hasNext()) {
      int i = itr.next();
      if (!visited[i]) {
        return isCyclicUtils(i, visited, stack);
      } else if (stack.contains(i)) {
        return true;
      }
    }
    return false;
  }

  public void topoSort() {
    boolean[] visited = new boolean[this.V];
    Stack stack = new Stack();
    for (int v = 0; v < this.V; v++) {
      if (!visited[v]) {
        topoSortUtils(v, visited, stack);
      }
    }

    while (!stack.isEmpty()) {
      System.out.print(stack.peek() + " ");
      stack.pop();
    }
  }

  private void topoSortUtils(int v, boolean[] visited, Stack stack) {
    visited[v] = true;

    Iterator<Integer> itr = adjMatrix[v].iterator();
    while (itr.hasNext()) {
      int i = itr.next();
      if (!visited[i]) {
        topoSortUtils(i, visited, stack);
      }
    }
    stack.push(v);

  }

  public static void main(String[] args) {
    Graph g1 = new Graph(4);
    g1.addEdge(2, 0);
    g1.addEdge(2, 3);
    g1.addEdge(0, 1);
    g1.addEdge(0, 2);
    g1.addEdge(1, 2);
    //g1.addEdge(3, 3);

    g1.printGraph();

    System.out.println("Print BFS:");
    int start = 0;
    g1.bfs(start);

    System.out.println("\nPrint DFS:");
    start = 0;
    g1.dfs(start);

    System.out.println("\nPrint DFS without stack:");
    start = 0;
    g1.dfsRecursion(start);

    if (g1.isCycilc()) {
      System.out.println("\nThis graph has cycle");
    } else {
      System.out.println("\nThis graph is not having cycle");
    }
  }
}
