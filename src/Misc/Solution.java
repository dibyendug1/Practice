package Misc;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {
  static class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
      this.data = data;
    }
  }

  /*
   * Complete the swapNodes function below.
   */
  static int[][] swapNodes(int[][] indexes, int[] queries) {
    /*
     * Write your code here.
     */
    Node root = new Node(1);
    Node tmp = null;
    LinkedList<Node> q = new LinkedList<Node>();
    q.add(root);
    int count = 1;
    for (int i = 0; i < indexes.length; i++) {

      tmp = q.peek();
      q.removeFirst();
      while (tmp == null) {
        tmp = q.peek();
        q.removeFirst();
      }
      if (indexes[i][0] == -1) {
        tmp.left = null;
      } else {
        tmp.left = new Node(indexes[i][0]);
        q.add(tmp.left);
        count++;
      }

      if (indexes[i][1] == -1) {
        tmp.right = null;
      } else {
        tmp.right = new Node(indexes[i][1]);
        q.add(tmp.right);
        count++;
      }

    }
    int[][] res = new int[queries.length][];
    int ii = 0;
    for (int lev : queries) {
      swapNodes(root, lev);
      int[] in = new int[count];
      inorder(root, in);
      res[ii] = in;
      ii++;
    }
    return res;
  }

  private static void inorder(Node root, int[] in) {
    if(root == null){
      return;
    }
    while (root!=null){

    }
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

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = Integer.parseInt(scanner.nextLine().trim());

    int[][] indexes = new int[n][2];

    for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
      String[] indexesRowItems = scanner.nextLine().split(" ");

      for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
        int indexesItem =
            Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
        indexes[indexesRowItr][indexesColumnItr] = indexesItem;
      }
    }

    int queriesCount = Integer.parseInt(scanner.nextLine().trim());

    int[] queries = new int[queriesCount];

    for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
      int queriesItem = Integer.parseInt(scanner.nextLine().trim());
      queries[queriesItr] = queriesItem;
    }

    int[][] result = swapNodes(indexes, queries);

    for (int resultRowItr = 0; resultRowItr < result.length; resultRowItr++) {
      for (int resultColumnItr = 0;
           resultColumnItr < result[resultRowItr].length; resultColumnItr++) {
        bufferedWriter
            .write(String.valueOf(result[resultRowItr][resultColumnItr]));

        if (resultColumnItr != result[resultRowItr].length - 1) {
          bufferedWriter.write(" ");
        }
      }

      if (resultRowItr != result.length - 1) {
        bufferedWriter.write("\n");
      }
    }

    bufferedWriter.newLine();

    bufferedWriter.close();
  }
}
