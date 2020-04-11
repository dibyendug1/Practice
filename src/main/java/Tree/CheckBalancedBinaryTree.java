package Tree;

/**
 * Check if a binary tree is balanced. Balanced tree - heights of two subtrees of any Nodes never differ by more than 1
 */
public class CheckBalancedBinaryTree {
  public static void main(String[] args) {
    int[] arr = { 1, 9, 2, 8, 3, 7, 4, 6, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
    //Node root = TreeUtils.createTree(arr, 0, arr.length - 1);
    BST tree = new BST();
    TreeUtils.createBST(tree, arr);
    Node root = tree.root;

    // O(NlogN) solution
    if (isBalanced(root)) {
      System.out.println("The tree is balanced.");
    } else {
      System.out.println("The tree is not balanced.");
    }

    // O(N) solution
    if (checkBalancedHeight(root) < 0) {
      System.out.println("The tree is not balanced.");
    } else {
      System.out.println("The tree is balanced.");
    }

  }

  private static int checkBalancedHeight(Node node) {
    if (node == null) {
      return 0;
    }

    int leftHeight = checkBalancedHeight(node.left);
    int rightHeight = checkBalancedHeight(node.right);

    if (Math.abs(leftHeight - rightHeight) > 1) {
      return Integer.MIN_VALUE;
    } else {
      return Math.max(leftHeight, rightHeight) + 1;
    }

  }

  private static boolean isBalanced(Node node) {
    if (node == null) {
      return true;
    }
    int leftHeight = height(node.left);
    int rightHeight = height(node.right);
    if (Math.abs(leftHeight - rightHeight) > 1) {
      return false;
    }

    return true && isBalanced(node.left) && isBalanced(node.right);

  }

  private static int height(Node node) {
    if (node == null) {
      return 0;
    }
    int leftHeight = height(node.left) + 1;
    int rightHeight = height(node.right) + 1;

    return leftHeight > rightHeight ? leftHeight : rightHeight;
  }
}
