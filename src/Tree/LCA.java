package Tree;

import java.io.IOException;
import java.util.ArrayList;

public class LCA extends TreeUtils {
    private static int findDistance(Node root, int node1, int node2) {
        Node LCA = findLCA(root, node1, node2);
        int dist1 = findDist(LCA, node1);
        int dist2 = findDist(LCA, node2);
        return dist1 + dist2;
    }

    private static int findDist(Node node, int data) {
        if (data < node.data) {
            return 1 + findDist(node.left, data);
        } else if (data > node.data) {
            return 1 + findDist(node.right, data);
        }
        return 0;
    }

    private static Node findLCA(Node node, int node1, int node2) {
        if (node == null) {
            return null;
        }
        if (node1 < node.data && node2 < node.data) {
            return findLCA(node.left, node1, node2);
        } else if (node1 > node.data && node2 > node.data) {
            return findLCA(node.right, node1, node2);
        } else return node;
    }

    private static void printCommonAncestors(Node root, int node1, int node2) {
        Node LCA = findLCA(root, node1, node2);
        findAncestors(root, LCA);
    }

    private static void findAncestors(Node node, Node lca) {
        if (lca.data < node.data) {
            findAncestors(node.left, lca);
        } else if (lca.data > node.data) {
            findAncestors(node.right, lca);
        }
        System.out.print(node.data + " ");

    }

    private static void findPathIterative(Node root, int i, ArrayList<Integer> path) throws IOException {
        Node node = root;
        while (node.data != i) {
            //System.out.print(node.data + " ");
            path.add(node.data);
            if (node.data > i) {
                node = node.left;
            } else if (node.data < i) {
                node = node.right;
            }
            if (node == null) {
                throw new IOException(i + " not found in the tree.");
            }
        }
        //System.out.println(node.data);
        path.add(node.data);
    }

    private static void printLeaves(Node node) {
        if (node.right == null && node.left == null) {
            System.out.print(node.data + " ");
        }
        if (node.left != null) {
            printLeaves(node.left);
        }
        if (node.right != null) {
            printLeaves(node.right);
        }

    }

    private static int findSize(Node node) {
        if (node == null) {
            return 0;
        }
        return findSize(node.left) + 1 + findSize(node.right);
    }

    public static void main(String[] args) {
        int[] nodes = {30, 20, 14, 67, 54, 34, 27, 87, 56, 23, 11, 57};
        BST tree1 = new BST();
        createBST(tree1, nodes);
        inorder(tree1.root);

        int node1 = 56;
        int node2 = 11;
        System.out.println("\nFinding LCA...");
        Node LCA = findLCA(tree1.root, node1, node2);
        System.out.printf("LCA of %d, %d is %d", node1, node2, LCA.data);

        int distance = findDistance(tree1.root, node1, node2);
        System.out.println("\nFind distance...");
        System.out.printf("Distance between %d, %d is %d", node1, node2,
                distance);

        System.out.printf("\nPrint common ancestors of %d, %d\n",
                node1, node2);
        printCommonAncestors(tree1.root, node1, node2);

        System.out.println("\nFind path without recursion:");
        ArrayList<Integer> path = new ArrayList<>();
        try {
            findPathIterative(tree1.root, node1, path);
            for (int element : path) {
                System.out.print(element + " ");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nPrint leaf nodes:");
        printLeaves(tree1.root);

        int size = findSize(tree1.root);
        System.out.printf("\nSize of the tree is %d", size);
    }
}
