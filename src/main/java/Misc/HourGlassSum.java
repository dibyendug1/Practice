package Misc;

import java.io.*;
import java.util.*;

public class HourGlassSum {
  //   * * *
  //     *
  //   * * *
  static int[][] hourGlass = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 1 }, { 2, 0 }, { 2, 1 }, { 2, 2 } };

  // Complete the hourglassSum function below.
  static int hourglassSum(int[][] arr) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        int sum = getSum(i, j, arr);
        max = max < sum ? sum : max;
      }
    }
    return max;
  }

  private static int getSum(int i, int j, int[][] arr) {
    int sum = 0;
    for (int[] p : hourGlass) {
      if (p[0] + i >= arr.length || p[1] + j >= arr[0].length) {
        return Integer.MIN_VALUE;
      }
      sum = sum + arr[p[0] + i][p[1] + j];
    }
    return sum;
  }

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    int[][] arr = new int[6][6];

    for (int i = 0; i < 6; i++) {
      String[] arrRowItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int j = 0; j < 6; j++) {
        int arrItem = Integer.parseInt(arrRowItems[j]);
        arr[i][j] = arrItem;
      }
    }

    int result = hourglassSum(arr);
    System.out.println(String.valueOf(result));
    scanner.close();
  }
}
