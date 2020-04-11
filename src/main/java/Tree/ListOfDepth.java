package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, design an algorithm which create a LinkedList of all the nodes at each depth.
 */
public class ListOfDepth {
  public static void main(String[] args) {
    int[] arr = { 1, 9, 2, 8, 3, 7, 4, 6, 5, 10, 11, 12, 13, 14, 15 };
    Node root = TreeUtils.createTree(arr, 0, arr.length - 1);

    ArrayList<LinkedList> depthList = new ArrayList<>();
    createDepthList(root, depthList, 0);
    System.out.println(depthList);

    ArrayList<LinkedList<Node>> depthListBFS = new ArrayList<>();
    createDepthListBFS(root, depthListBFS);
    System.out.println(depthListBFS);

  }

  private static void createDepthListBFS(Node root, ArrayList<LinkedList<Node>> depthListBFS) {
    LinkedList<Node> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      depthListBFS.add(queue);
      LinkedList<Node> parent = queue;
      queue = new LinkedList<>();
      for (Node n : parent) {
        if (n.left != null) {
          queue.add(n.left);
        }
        if (n.right != null) {
          queue.add(n.right);
        }
      }
    }
  }

  private static void createDepthList(Node node, ArrayList<LinkedList> depthList, int i) {
    if (node == null) {
      return;
    }
    if (depthList.size() < i + 1) {
      depthList.add(new LinkedList<Node>());
    }
    depthList.get(i).add(node);
    createDepthList(node.left, depthList, i + 1);
    createDepthList(node.right, depthList, i + 1);
  }
}
