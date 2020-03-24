package Graph;

public class TopologicalSort {
  public static void main(String[] args) {
    Graph g1 = new Graph(6);
    g1.addEdge(5,0);
    g1.addEdge(5,2);
    g1.addEdge(4,0);
    g1.addEdge(4,1);
    g1.addEdge(2,3);
    g1.addEdge(3,1);

    g1.printGraph();

    System.out.println("Topological sort:");
    g1.topoSort();
  }
}
