package HandsOn;

public class BST {
  Node root;

  public static void main(String[] args) {
    BST tree1 = new BST();
    int[] nodes = { 46, 32, 54, 23, 56, 78, 34 };
    for (int n : nodes) {
      tree1.root = tree1.insert(n);
    }

    System.out.println("Print indorder:");
    tree1.inorder();

    int delete = 46;
    System.out.println("\nDelete node:" + delete);
    tree1.root = tree1.delete(delete);

    System.out.println("Print inorder:");
    tree1.inorder();
  }

  private Node delete(int i) {
    Node node = this.root;
    return deleteUtils(node, i);
  }

  private Node deleteUtils(Node node, int i) {
    if (node == null) {
      return node;
    }
    if (i < node.getData()) {
      node.setLeft(deleteUtils(node.getLeft(), i));
    } else if (i > node.getData()) {
      node.setRight(deleteUtils(node.getRight(), i));
    } else {
      if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      }

      Node inOrderPred = findInorderPred(node);
      node.data = inOrderPred.getData();
      node.setLeft(deleteUtils(node.getLeft(), inOrderPred.getData()));
    }
    return node;
  }

  private Node findInorderPred(Node node) {
    Node tmp = node.getLeft();
    while (tmp.getRight() != null) {
      tmp = tmp.getRight();
    }
    return tmp;
  }

  private Node findNode(int i) {
    return findNodeUtils(this.root, i);
  }

  private Node findNodeUtils(Node node, int i) {
    if (node == null) {
      System.out.println("Not Found");
      return node;
    }
    if (i < node.getData()) {
      return findNodeUtils(node.getLeft(), i);
    } else if (i > node.getData()) {
      return findNodeUtils(node.getRight(), i);
    }
    return node;
  }

  private void inorder() {
    Node node = this.root;
    inorderUtils(node);
  }

  private void inorderUtils(Node node) {
    if (node == null) {
      return;
    }

    inorderUtils(node.getLeft());
    System.out.print(node.data + " ");
    inorderUtils(node.getRight());
  }

  private Node insert(int i) {
    Node node = this.root;
    return insertUtils(i, node);
  }

  private Node insertUtils(int i, Node node) {
    if (node == null) {
      return new Node(i);
    }
    if (i < node.data) {
      node.left = insertUtils(i, node.getLeft());
    } else if (i > node.data) {
      node.right = insertUtils(i, node.getRight());
    }
    return node;
  }
}
