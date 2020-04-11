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
public class FindSubTree {
  public static boolean isSubtree(Node s, Node t) {
    String tree1 = inorder(s);
    String tree2 = inorder(t);
    System.out.println(tree1);
    System.out.println(tree2);
    return tree1.indexOf(tree2) != -1 ? true : false;
  }

  private static boolean treeTraverse(Node s, Node t) {
    if (t == null) {
      return true;
    }
    if (s == null) {
      return false;
    }
    boolean ret = false;
    if (s.data == t.data) {
      ret = exactMatch(s, t);
    }

    return ret || treeTraverse(s.left, t) || treeTraverse(s.right, t);

  }

  private static boolean exactMatch(Node s, Node t) {
    if (s == null && t == null) {
      return true;
    }
    if (t == null || s == null) {
      return false;
    }
    if (s.data != t.data) {
      return false;
    }

    boolean left = exactMatch(s.left, t.left);
    boolean right = exactMatch(s.right, t.right);

    if (left && right) {
      return true;
    }
    return false;
  }

  private static String inorder(Node node) {
    if (node == null) {
      return "X";
    }
    StringBuilder sb = new StringBuilder();

    sb.append(inorder(node.left));
    sb.append(node.data);
    sb.append(inorder(node.right));

    return sb.toString();
  }

  public static void main(String[] args) {
    int[] arr = { 1, 9, 2, 8, 3, 7, 4, 6, 5, 10, 11, 12, 13, 14, 15 };
    Node root = TreeUtils.createTree(arr, 0, arr.length - 1);
    TreeUtils.inorder(root);
    TreeUtils.printHierarchy(root);

    int[] arr2 = { 1, 9, 2, 3 };
    Node root2 = TreeUtils.createTree(arr2, 0, arr2.length - 1);
    TreeUtils.inorder(root2);
    TreeUtils.printHierarchy(root2);

    System.out.println(isSubtree(root, root2));

    System.out.println("Exact Match: " + isSubtreeExact(root, root2));

  }

  private static boolean isSubtreeExact(Node s, Node t) {
    return treeTraverse(s, t);
  }

}
