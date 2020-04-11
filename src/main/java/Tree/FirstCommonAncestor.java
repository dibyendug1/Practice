package Tree;

/**
 * First Common Ancestor in binary tree.
 */
public class FirstCommonAncestor {
  public static void main(String[] args) {
    int[] arr = { 1, 9, 2, 8, 3, 7, 4, 6, 5, 10, 11, 12, 13, 14, 15 };
    Node root = TreeUtils.createTree(arr, 0, arr.length - 1);
    TreeUtils.inorder(root);
    TreeUtils.printHierarchy(root);
    //Node n = firstCommonAncestor(root, 1, 4);
    Node n = firstCommonAncestor2(root, 1, 100);
    System.out.println(n);
  }

  private static Node firstCommonAncestor2(Node root, int c1, int c2) {
    if (root == null) {
      return root;
    }
/*    if (!find(root, c1) || !find(root, c2)) {
      return null;
    }*/
    if (root.data == c1 || root.data == c2) {
      return root;
    }
    return commonAncestor(root, c1, c2);
  }

  private static Node commonAncestor(Node root, int c1, int c2) {
    if (root == null || root.data == c1) {
      return root;
    }

    Node left = commonAncestor(root.left, c1, c2);
    if (left != null && find(root.right, c2)) {
      return root;
    }
    Node right = commonAncestor(root.right, c1, c2);
    if (right != null && find(root.left, c2)) {
      return root;
    }

    return left == null ? right : left;
  }

  // Time complexity O(N)
  private static Node firstCommonAncestor(Node root, int c1, int c2) {
    boolean isPresent1 = find(root, c1);
    boolean isPresent2 = find(root, c2);
    if (isPresent1 != isPresent2) {
      return null;
    }
    boolean isLeft1 = find(root.left, c1);
    boolean isLeft2 = find(root.left, c2);
    if (isLeft1 != isLeft2) {
      return root;
    }
    return isLeft1 ? firstCommonAncestor(root.left, c1, c2) : firstCommonAncestor(root.right, c1, c2);
  }

  private static boolean find(Node root, int data) {
    if (root == null) {
      return false;
    }
    if (root.data == data) {
      return true;
    }
    return find(root.left, data) || find(root.right, data);
  }
}
