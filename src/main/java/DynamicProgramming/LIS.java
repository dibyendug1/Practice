package DynamicProgramming;

import java.util.ArrayList;

public class LIS {
  static int lis;

  public static void main(String[] args) {
    int[] arr = { 10, 9, 33, 11, 21, 41,2 };
    System.out.println("Longest increasing subsequence length is:" + lis(arr));

    System.out.println(
        "Non recursive Longest increasing subsequence length :" + nonRecurlis(
            arr));
    int[] arr2 = { 1, 101, 2, 3, 100, 4, 5 };
    System.out.println("Longest increasing subsequene sum:" + lisSum(arr));
  }

  private static int lisSum(int[] arr) {
    int n = arr.length;
    int[] lisSum = new int[n];
    int[] seq = new int[n];
    for (int i = 0; i < n; i++) {
      lisSum[i] = arr[i];
      seq[i] = i;
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < i - 1; j++) {
        if (arr[i] > arr[j] && lisSum[j] + arr[i] > lisSum[i]) {
          lisSum[i] = lisSum[j] + arr[i];
          seq[i] = j;
        }
      }
    }

    int max = Integer.MIN_VALUE;
    int index = 0;
    for (int i = 0; i < n; i++) {
      if (lisSum[i] > max) {
        max = lisSum[i];
        index = i;
      }
    }
    printSeq(seq, index);
    System.out.println();
    return max;
  }

  private static int nonRecurlis(int[] arr) {
    int n = arr.length;
    int[] seq = new int[n];

    if (n == 1) {
      return 1;
    }
    int[] lis = new int[n];

    for (int i = 0; i < n; i++) {
      lis[i] = 1;
      seq[i] = i;
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 0; j < i - 1; j++) {
        if (arr[i - 1] > arr[j] && lis[j] + 1 > lis[i - 1]) {
          lis[i - 1] = 1 + lis[j];
          seq[i - 1] = j;
        }
      }
    }

    int max = Integer.MIN_VALUE;
    int index = 0;
    for (int i = 0; i < n; i++) {
      if (lis[i] > max) {
        max = lis[i];
        index = i;
      }
    }
    printSeq(seq, index);
    System.out.println();
    return max;

  }

  private static void printSeq(int[] seq, int index) {
    if (index == seq[index]) {
      System.out.print(index + " ");
      return;
    }
    printSeq(seq, seq[index]);
    System.out.print(index + " ");
  }

  private static int lis(int[] arr) {
    lis = 1;
    lisUtils(arr, arr.length);
    return lis;
  }

  private static int lisUtils(int[] arr, int length) {
    if (length == 1) {
      return 1;
    }

    int tmp, prelis = 1;

    for (int i = 1; i < length; i++) {
      tmp = lisUtils(arr, i);
      if (arr[length - 1] > arr[i - 1] && tmp + 1 > prelis) {
        prelis = tmp + 1;
      }
    }

    if (lis < prelis) {
      lis = prelis;
    }

    return prelis;
  }
}
