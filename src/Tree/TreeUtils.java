package Tree;

import Tree.AVLNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

class TreeUtils {

  static void createBST(BST tree1, int[] nodes) {
    for (int i : nodes) {
      tree1.insert(i);
    }
  }

  static void inorder(Node node) {
    if (node == null) {
      return;
    }
    inorder(node.left);
    System.out.print(node.data + " ");
    inorder(node.right);
  }

  static void inorder(AVLNode node) {
    if (node == null) {
      return;
    }
    inorder(node.left);
    System.out.print(node.data + " ");
    inorder(node.right);
  }

  static void postorder(Node node) {
    if (node == null) {
      return;
    }
    postorder(node.left);
    postorder(node.right);
    System.out.print(node.data + " ");
  }

  static void preorder(Node node) {
    if (node == null) {
      return;
    }
    System.out.print(node.data + " ");
    preorder(node.left);
    preorder(node.right);
  }

  Node findPredecessor(Node right, Node max) {
    if (right == null) {
      return max;
    }
    if (right.data > max.data) {
      max = right;
    }
    return findPredecessor(right.right, max);
  }

  Node findNodeUtils(Node root, int i) {
    if (root.data > i) {
      return findNodeUtils(root.left, i);
    } else if (root.data < i) {
      return findNodeUtils(root.right, i);
    }
    return root;
  }

  static void dfs(Node root) {
    LinkedList<Node> st = new LinkedList<Node>();
    st.push(root);
    while (!st.isEmpty()) {
      Node tmp = st.peek();
      st.pop();
      if (tmp != null) {
        st.push(tmp.right);
        st.push(tmp.left);
        System.out.print(tmp.data + " ");
      }

    }
  }
}
