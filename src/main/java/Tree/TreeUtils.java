package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

  /**
   * Find all the nodes in the tree which are at given distance from the target node.
   *
   * @param root
   * @param dist
   * @param target
   * @return list of nodes
   */
  static List<Node> findNodesAtDistance(Node root, int dist, int target) {
    List<Node> resultNodes = new ArrayList<>();
    if (root == null) {
      return resultNodes;
    }
    findNodesAtDistanceAbove(root, dist, target, resultNodes);
    return resultNodes;
  }

  private static int findNodesAtDistanceAbove(Node node, int dist, int target, List<Node> resultNodes) {
    if (node == null) {
      return -1;
    }
    if (node.data == target) {
      // Search down to find the nodes at given dist
      findNodesAtDistanceDown(node, dist, target, -1, resultNodes);
      return 0;
    }

    // Search above to find the nodes at given dist
    int leftDist = findNodesAtDistanceAbove(node.left, dist, target, resultNodes);
    if (leftDist != -1) {
      if (leftDist + 1 == dist) {
        //System.out.println(String.format("%d node of %d is: %d", dist, target, node.data));
        resultNodes.add(node);
      } else if (leftDist + 1 < dist) {
        findNodesAtDistanceDown(node.right, dist, target, leftDist + 1, resultNodes);
      }
      return leftDist + 1;
    }

    int rightDist = findNodesAtDistanceAbove(node, dist, target, resultNodes);
    if (rightDist != -1) {
      if (rightDist + 1 == dist) {
        //System.out.println(String.format("%d node of %d is: %d", dist, target, node.data));
        resultNodes.add(node);
      } else if (rightDist + 1 < dist) {
        findNodesAtDistanceDown(node.left, dist, target, rightDist + 1, resultNodes);
      }
      return rightDist + 1;
    }

    return -1;
  }

  private static void findNodesAtDistanceDown(Node node, int dist, int target, int curDist, List<Node> resultNodes) {
    if (curDist + 1 > dist || node == null) {
      return;
    }
    if (curDist + 1 == dist) {
      System.out.println(String.format("%d node of %d is: %d", dist, target, node.data));
      resultNodes.add(node);
      return;
    }
    findNodesAtDistanceDown(node.left, dist, target, curDist + 1, resultNodes);
    findNodesAtDistanceDown(node.right, dist, target, curDist + 1, resultNodes);
  }
}
