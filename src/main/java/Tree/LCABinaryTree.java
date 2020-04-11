package Tree;

/**
 * Definition for a binary tree node.
 * public class Node {
 * int val;
 * Node left;
 * Node right;
 * Node(int x) { val = x; }
 * }
 */
public class LCABinaryTree {
  public static Node lowestCommonAncestor(Node root, Node p, Node q) {
    if (root == null) {
      return root;
    }

    if (!findNode(root, p) || !findNode(root, q)) {
      return null;
    }

    return lcahealper(root, p, q);
  }

  private static Node lcahealper(Node root, Node p, Node q) {
    if (root == null || root == p || root == q) {
      return root;
    }
    boolean isLeft1 = findNode(root.left, p);
    boolean isLeft2 = findNode(root.left, q);

    if (isLeft1 != isLeft2) {
      return root;
    }

    return isLeft1 ? lcahealper(root.left, p, q) : lcahealper(root.right, p, q);

  }

  private static boolean findNode(Node root, Node node) {
    if (root == null) {
      return false;
    }
    if (root == node) {
      return true;
    }

    return findNode(root.left, node) || findNode(root.right, node);
  }

  public static void main(String[] args) {
    int[] arr = { 1, 9, 2, 8, 3, 7, 4, 6, 5, 10, 11, 12, 13, 14, 15 };
    Node root = TreeUtils.createTree(arr, 0, arr.length - 1);
    TreeUtils.inorder(root);
    TreeUtils.printHierarchy(root);

    Node p = getNode(root, 1);
    Node q = getNode(root, 4);

    Node node = lowestCommonAncestor(root, p, q);

    System.out.println(node);

  }

  private static Node getNode(Node root, int i) {
    if (root == null || root.data == i) {
      return root;
    }

    Node p = getNode(root.left, i);
    return p != null ? p : getNode(root.right, i);

  }
}