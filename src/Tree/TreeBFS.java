package Tree;

import java.util.*;
import java.io.*;

class TNode {
  TNode left;
  TNode right;
  int data;

  TNode(int data) {
    this.data = data;
    left = null;
    right = null;
  }
}

public class TreeBFS {
  public static void levelOrder(TNode root) {
    LinkedList<TNode> queue = new LinkedList<TNode>();
    queue.add(root);

    while (!queue.isEmpty()) {
      TNode tmp = queue.poll();
      System.out.print(tmp.data + " ");
      if (tmp.left != null) {
        queue.add(tmp.left);
      }
      if (tmp.right != null) {
        queue.add(tmp.right);
      }
    }
  }

  public static TNode insert(TNode root, int data) {
    if (root == null) {
      return new TNode(data);
    } else {
      TNode cur;
      if (data <= root.data) {
        cur = insert(root.left, data);
        root.left = cur;
      } else {
        cur = insert(root.right, data);
        root.right = cur;
      }
      return root;
    }
  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int t = scan.nextInt();
    TNode root = null;
    while (t-- > 0) {
      int data = scan.nextInt();
      root = insert(root, data);
    }
    scan.close();
    levelOrder(root);
  }
}
