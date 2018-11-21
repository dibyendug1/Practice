package Tree;

public class BST extends TreeUtils {
  Node root;

  BST() {
    root = null;
  }

  void insert(int data) {
    root = insertUtils(root, data);
  }

  private Node insertUtils(Node node, int data) {
    if (node == null) {
      return new Node(data);
    }
    if (node.data > data) {
      node.left = insertUtils(node.left, data);
    } else if (node.data < data) {
      node.right = insertUtils(node.right, data);
    } else {
      System.out.printf("Duplicate nodes are not allowed %d\n", data);
      return null;
    }
    return node;
  }

  private void delete(int i) {
    root = deleteUtils(root, i);
  }

  private Node deleteUtils(Node node, int i) {
    if (node == null) {
      return null;
    }
    if (i < node.data) {
      node.left = deleteUtils(node.left, i);
    } else if (i > node.data) {
      node.right = deleteUtils(node.right, i);
    } else {
      if (node.left == null) {
        return node.right;
      } else if (node.right == null) {
        return node.left;
      }
      Node predecessor = inorderPredecessor(i);
      node.data = predecessor.data;
      node.left = deleteUtils(node.left, predecessor.data);
    }
    return node;
  }

  private Node inorderPredecessor(int i) {
    Node node = findNode(i);
    Node leftSub = node.left;
    return findPredecessor(leftSub.right, leftSub);
  }

  private Node findNode(int i) {
    return findNodeUtils(root, i);
  }

  boolean checkBST(Node root) {
    int min = 0;
    int max = (int) Math.pow(10, 4);

    return checkBST(root, min, max);
  }

  private boolean checkBST(Node root, int min, int max) {
    if (root == null) {
      return true;
    }
    if (root.data < min || root.data > max)
      return false;
    if (root.left == null && root.right == null) {
      return true;
    } else {
      if (root.left != null && root.right == null) {
        return (root.left.data <= root.data) && checkBST(root.left, min,
            root.data - 1);
      } else if (root.right != null && root.left == null) {
        return (root.right.data >= root.data) && checkBST(root.right,
            root.data + 1, max);
      } else {
        return (root.data >= root.left.data) && (root.data <= root.right.data)
            && checkBST(root.left, min, root.data - 1) && checkBST(root.right,
            root.data + 1, max);
      }
    }
  }

  public static void main(String[] args) {
    BST tree1 = new BST();
    int[] nodes = { 30, 20, 14, 67, 54, 34, 27, 87, 56, 23, 11 };
    createBST(tree1, nodes);
    System.out.println("Print Inorder...");
    inorder(tree1.root);
    System.out.println("\nPrint Preorder...");
    preorder(tree1.root);
    System.out.println("\nPrint Postorder...");
    postorder(tree1.root);

    int delete = 30;
    System.out.printf("\nDeleting...%d", delete);
    tree1.delete(delete);
    System.out.println("\nPrint Inorder...");
    inorder(tree1.root);
    System.out.println("\nPrint dfs:");
    dfs(tree1.root);
    System.out.println("\nSwap nodes:");
    swapNodes(tree1.root, 4);
    System.out.println("\nPrint Inorder...");
    inorder(tree1.root);
  }

  private static void swapNodes(Node root, int level) {
    int lev = 1;
    swapTreeNodes(root, lev, level);
  }

  private static void swapTreeNodes(Node root, int lev, int level) {
    if (root == null) {
      return;
    }
    if (lev == level - 1) {
      Node tmp = root.left;
      root.left = root.right;
      root.right = tmp;
    } else {
      lev++;
      swapTreeNodes(root.left, lev, level);
      swapTreeNodes(root.right, lev, level);
    }
  }
}