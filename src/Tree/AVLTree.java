package Tree;

public class AVLTree extends TreeUtils {
  AVLNode root;

  public AVLTree() {
    root = null;
  }

  private void insert(int i) {
    root = insertUtils(root, i);
  }

  private AVLNode insertUtils(AVLNode node, int i) {
    if (node == null) {
      return new AVLNode(i);
    }

    if (i < node.data) {
      node.left = insertUtils(node.left, i);
    } else if (i > node.data) {
      node.right = insertUtils(node.right, i);
    } else {
      System.out.println("Duplicate node is not allowed");
      return null;
    }

    return balanceTheTree(node);
  }

  private AVLNode rotateLeft(AVLNode node) {
    AVLNode rightChild = node.right;
    node.right = rightChild.left;
    rightChild.left = node;
    //Update heights
    node.height = 1 + max(height(node.left), height(node.right));
    rightChild.height =
        1 + max(height(rightChild.left), height(rightChild.right));
    return rightChild;
  }

  private AVLNode rotateRight(AVLNode node) {
    AVLNode leftChild = node.left;
    node.left = leftChild.right;
    leftChild.right = node;
    //Update heights
    node.height = 1 + max(height(node.left), height(node.right));
    leftChild.height = 1 + max(height(leftChild.left), height(leftChild.right));
    return leftChild;
  }

  private int getBalance(AVLNode node) {
    return height(node.left) - height((node.right));
  }

  int height(AVLNode node) {
    if (node == null) {
      return 0;
    }
    return node.height;
  }

  int max(int x, int y) {
    return x > y ? x : y;
  }

  private void delete(int del) {
    root = deleteUtils(root, del);
  }

  private AVLNode deleteUtils(AVLNode node, int del) {
    if (node == null) {
      return node;
    }
    if (del < node.data) {
      node.left = deleteUtils(node.left, del);
    } else if (del > node.data) {
      node.right = deleteUtils(node.right, del);
    } else {
      if (node.left == null) {
        return node.right;
      } else if (node.right == null) {
        return node.left;
      }
      AVLNode predecessor = inorderPredecessor(node);
      node.data = predecessor.data;
      node.left = deleteUtils(node.left, predecessor.data);
    }
    return balanceTheTree(node);

  }

  private AVLNode balanceTheTree(AVLNode node) {
    // Update height
    node.height = max(height(node.left), height(node.right)) + 1;
    // Get balance factor
    int balance = getBalance(node);

    if (balance > 1 && getBalance(node.left) >= 0) {
      // Rotate right
      node = rotateRight(node);
    } else if (balance < -1 && getBalance(node.right) <= 0) {
      // Rotate left
      node = rotateLeft(node);
    } else if (balance > 1 && getBalance(node.left) < 0) {
      // Rotate left and then right
      node.left = rotateLeft(node.left);
      return rotateRight(node);
    } else if (balance < -1 && getBalance(node.right) > 0) {
      // Rotate right and then left
      node.right = rotateRight(node.right);
      return rotateLeft(node);
    }
    return node;
  }

  private AVLNode findNode(int data) {
    return findNodeUtils(root, data);
  }

  public AVLNode findNodeUtils(AVLNode root, int i) {
    if (i < root.data) {
      return findNodeUtils(root.left, i);
    } else if (i > root.data) {
      return findNodeUtils(root.right, i);
    }
    return root;
  }

  private AVLNode inorderPredecessor(AVLNode node) {
    AVLNode max = node.left;
    AVLNode predecessor = findPredecessor(node.left.right, max);
    return predecessor;
  }

  private AVLNode findPredecessor(AVLNode right, AVLNode max) {
    if (right == null) {
      return max;
    }
    if (max.data < right.data) {
      max = right;
    }
    return findPredecessor(right.right, max);
  }

  public static void main(String[] args) {
    AVLTree tree = new AVLTree();
    int[] nodes = { 30, 20, 14, 67, 54, 34, 27, 87, 56, 23, 11 };
    for (int i : nodes) {
      tree.insert(i);
    }
    System.out.println("Print Inorder...");
    TreeUtils.inorder(tree.root);

    int del = 23;
    tree.delete(del);
    System.out.println("\nPrint Inorder...");
    TreeUtils.inorder(tree.root);

    del = 27;
    tree.delete(del);
    System.out.println("\nPrint Inorder...");
    TreeUtils.inorder(tree.root);
  }
}
