package HandsOn;

public class Node {
  int data;
  Node right;
  Node left;
  int height;

  public int getHeight() {
    return height;
  }

  public Node(int data) {
    this.data = data;
    this.height = 1;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public Node getLeft() {
    return left;
  }

  public void setLeft(Node left) {
    this.left = left;
  }

  public Node getRight() {
    return right;
  }

  public void setRight(Node right) {
    this.right = right;
  }

  public int getData() {
    return this.data;
  }

}
