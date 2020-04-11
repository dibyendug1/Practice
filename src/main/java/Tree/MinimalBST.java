package Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a sorted array with unique integer elements, write an algorithm to create a binary search tree with minimal
 * height
 */
public class MinimalBST {
  public static void main(String[] args) {
    int[] arr = { 1, 9, 2, 7, 3, 8, 5, 4, 6 };
    sortArray(arr);
    System.out.println(Arrays.toString(arr));
    Node root = createMinimalBST(arr, 0, arr.length - 1);
    printTree(root);
  }

  private static void printTree(Node node) {
    printInorder(node);
    //printBFS(node);
  }

  private static void printInorder(Node node) {
    if (node == null) {
      return;
    }

    printInorder(node.left);
    System.out.println(node);
    printInorder(node.right);
  }

  private static void printBFS(Node node) {
    Queue<Node> queue = new LinkedList<>();

    queue.add(node);
    while (!queue.isEmpty()) {
      Node current = queue.remove();
      System.out.println(current);
      if (current.left != null) {
        queue.add(current.left);
      }

      if (current.right != null) {
        queue.add(current.right);
      }
    }
  }

  private static Node createMinimalBST(int[] arr, int start, int end) {
    if (end < start) {
      return null;
    }
    int mid = (start + end) / 2;
    Node n = new Node(arr[mid]);
    n.left = createMinimalBST(arr, start, mid - 1);
    n.right = createMinimalBST(arr, mid + 1, end);
    return n;
  }

  private static void sortArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < i; j++) {
        if (arr[i] < arr[j]) {
          arr[i] = arr[i] + arr[j];
          arr[j] = arr[i] - arr[j];
          arr[i] = arr[i] - arr[j];
        }
      }
    }
  }
}
