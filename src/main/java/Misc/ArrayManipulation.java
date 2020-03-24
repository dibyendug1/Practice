package Misc;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ArrayManipulation {

  // Complete the arrayManipulation function below.
  static long arrayManipulation(int n, int[][] queries) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < queries.length - 1; i++) {
      int p = queries[i][0];
      int q = queries[i][1];
      int val = queries[i][2];
      for (int j = i + 1; j < queries.length; j++) {
        if (p > queries[j][0] && p < queries[j][1]) {
          val += queries[j][2];
        } else if (q > queries[j][0] && q < queries[j][1]) {
          val += queries[j][2];
        } else if (queries[j][0] > p && queries[j][0] < q) {
          val += queries[j][2];
          p = queries[j][0];
        } else if (queries[j][1] > p && queries[j][1] < q) {
          val += queries[j][2];
          q = queries[j][1];
        }
      }
      if (max < val) {
        max = val;
      }
    }
    return max;

    /*int[][] arr = new int[n][n];
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < queries.length; i++) {
      for (int p = queries[i][0] - 1; p < queries[i][1]; p++) {
        for (int q = queries[i][0] - 1; q < queries[i][1]; q++) {
          arr[p][q] += queries[i][2];
          if (max < arr[p][q]) {
            max = arr[p][q];
          }
        }
      }
    }
    return max;*/
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    String[] nm = scanner.nextLine().split(" ");

    int n = Integer.parseInt(nm[0]);

    int m = Integer.parseInt(nm[1]);

    int[][] queries = new int[m][3];

    for (int i = 0; i < m; i++) {
      String[] queriesRowItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int j = 0; j < 3; j++) {
        int queriesItem = Integer.parseInt(queriesRowItems[j]);
        queries[i][j] = queriesItem;
      }
    }

    long result = arrayManipulation(n, queries);

    System.out.println(result);

    scanner.close();
  }
}
