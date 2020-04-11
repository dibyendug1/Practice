package Tree;

public class InorderSuccessorBST {
  public static void main(String[] args) {
    int[] arr = { 1, 9, 2, 8, 3, 7, 4, 6, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
    //Node root = TreeUtils.createTree(arr, 0, arr.length - 1);
    BST tree = new BST();
    TreeUtils.createBST(tree, arr);
    TreeUtils.inorder(tree.root);

    int target = 8;
    System.out.println("\nInorder successor of " + target + " is:");
    Node targetNode = findNode(tree.root, target);
    if (targetNode != null) {
      Node successor = findInorderSuccessor(tree.root, targetNode);
      System.out.println(successor);
    }

  }

  // Left most child of right subtree
  private static Node findInorderSuccessor(Node root, Node targetNode) {
    Node node = targetNode.right;
    if (node == null) {
      Node successor = findParent(root, targetNode);
      if (successor.equals(targetNode)) {
        return null;
      }
      return successor;
    }
    while (node != null && node.left != null) {
      node = node.left;
    }

    return node;
  }

  private static Node findParent(Node root, Node targetNode) {
    if (root.data == targetNode.data) {
      return targetNode;
    }
    if (targetNode.data < root.data) {
      Node left = findParent(root.left, targetNode);
      if (left.equals(targetNode)) {
        return root;
      } else if (left != null) {
        return left;
      }
    }

    if (targetNode.data > root.data) {
      return findParent(root.right, targetNode);
    }
    return null;
  }

  private static Node findNode(Node node, int target) {
    if (node != null && node.data == target) {
      return node;
    }
    if (node.data > target) {
      return findNode(node.left, target);
    }
    if (node.data < target) {
      return findNode(node.right, target);
    }
    return null;
  }

}
