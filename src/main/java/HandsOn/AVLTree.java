package HandsOn;

public class AVLTree {
  Node root;

  public static void main(String[] args) {
    AVLTree tree1 = new AVLTree();
    tree1.root = tree1.insert(40);
  }

  private Node insert(int i) {
    Node node = this.root;
    return insertUtils(node, i);
  }

  private Node insertUtils(Node node, int i) {
    if (node == null) {
      return new Node(i);
    }

    if (i < node.getData()) {
      node.left = insertUtils(node.getLeft(), i);
    } else if (i > node.getData()) {
      node.right = insertUtils(node.getRight(), i);
    }
    node.height =
        1 + max(getHeight(node.getLeft()), getHeight(node.getRight()));
    int balance = getBalance(node);

    if (balance > 1 && i < node.getLeft().getData()) {
      return rotateRight(node);
    } else if (balance < -1 && i > node.getRight().getData()) {
      return rotateLeft(node);
    } else if (balance > 1 && i > node.getLeft().getData()) {
      node.left = rotateLeft(node.left);
      return rotateRight(node);
    } else if (balance < -1 && i < node.getRight().getData()) {
      node.right = rotateRight(node.right);
      return rotateLeft(node);
    }
    return node;
  }

  private Node rotateLeft(Node node) {
    Node rightChild = node.getRight();
    node.right = rightChild.left;
    rightChild.left = node;

    node.height =
        1 + max(getHeight(node.getLeft()), getHeight(node.getRight()));

    rightChild.height = 1 + max(getHeight(rightChild.getRight()),
        getHeight(rightChild.getLeft()));
    return rightChild;
  }

  private Node rotateRight(Node node) {
    Node leftChild = node.getLeft();
    node.left = leftChild.right;
    leftChild.right = node;

    node.height =
        1 + max(getHeight(node.getLeft()), getHeight(node.getRight()));

    leftChild.height = 1 + max(getHeight(leftChild.getRight()),
        getHeight(leftChild.getLeft()));
    return leftChild;

  }

  private int getBalance(Node node) {
    return getHeight(node.getLeft()) - getHeight(node.getRight());
  }

  private int max(int height, int height1) {
    return height > height1 ? height : height1;
  }

  private int getHeight(Node node) {
    if (node == null) {
      return 0;
    }
    return node.height;
  }
}
