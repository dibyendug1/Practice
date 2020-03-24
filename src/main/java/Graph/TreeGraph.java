package Graph;

import java.util.Iterator;
import java.util.LinkedList;

public class TreeGraph extends Graph {
  public TreeGraph(int V) {
    super(V);
  }

  private void findNodes(int start, int level) {
    boolean[] visited = new boolean[this.V];
    int[] l = new int[this.V];
    LinkedList<Integer> queue = new LinkedList<>();
    queue.add(start);
    l[start] = 0;

    while (!queue.isEmpty()) {
      int n = queue.poll();
      visited[n] = true;
      if (l[n] == level) {
        System.out.print(n + " ");
      }

      Iterator<Integer> itr = adjMatrix[n].iterator();
      while (itr.hasNext()) {
        int i = itr.next();
        if (!visited[i]) {
          queue.add(i);
          l[i] = l[n] + 1;
        }
      }
    }
  }

  private void findNodesAt(int start, int level) {
    boolean[] visited = new boolean[this.V];
    int lev = 0;
    find(start, level, visited, lev);
  }

  private void find(int n, int level, boolean[] visited, int lev) {
    visited[n] = true;
    if (lev == level) {
      System.out.print(n + " ");
    }
    Iterator<Integer> itr = adjMatrix[n].iterator();
    lev++;
    while (itr.hasNext()) {
      int i = itr.next();
      if (!visited[i]) {
        find(i, level, visited, lev);
      }
    }
  }

  public static void main(String[] args) {
    TreeGraph t1 = new TreeGraph(7);
    t1.addEdge(0, 1);
    t1.addEdge(0, 2);
    t1.addEdge(1, 3);
    t1.addEdge(1, 4);
    t1.addEdge(1, 5);
    t1.addEdge(2, 6);

    System.out.println("Print graph:");
    t1.printGraph();

    int level = 2;
    int start = 0;
    System.out.printf("\nNodes at level %d are\n", level);
    t1.findNodes(start, level);
    System.out.printf("\nNodes at level %d are\n", level);
    t1.findNodesAt(start, level);
  }

}
