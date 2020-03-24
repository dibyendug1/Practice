package DynamicProgramming;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Scanner;

/**
 * Stair case with n steps
 * Can hop either 1 step or 2 steps or 3 steps
 * Count number of ways
 */

public class StairCaseClimb {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    if (sc.hasNext()) {
      int n = sc.nextInt();
      long start = System.currentTimeMillis();
      long count = countWaysRecursive(n);
      long end = System.currentTimeMillis();
      System.out.println("Stair case can be climbed in " + count + " ways. Computed in " + (end - start) + " ms");
      start = System.currentTimeMillis();
      long nonRecursiveCount = countWays(n);
      end = System.currentTimeMillis();
      System.out
          .println("Stair case can be climbed in " + nonRecursiveCount + " ways. Computed in " + (end - start) + " ms");
      start = System.currentTimeMillis();
      long nonRecursiveCount2 = countWaysSpace(n);
      end = System.currentTimeMillis();
      System.out.println(
          "Stair case can be climbed in " + nonRecursiveCount2 + " ways. Computed in " + (end - start) + " " + "ms");

    }
  }

  private static long countWays(int n) {
    long[] countNoWays = new long[n + 1];
    countNoWays[0] = 1;
    for (int i = 1; i <= n; i++) {
      if (i - 1 >= 0) {
        countNoWays[i] += countNoWays[i - 1];
      }
      if (i - 2 >= 0) {
        countNoWays[i] += countNoWays[i - 2];
      }
      if (i - 3 >= 0) {
        countNoWays[i] += countNoWays[i - 3];
      }
    }
    return countNoWays[n];
  }

  //Space optimized
  private static long countWaysSpace(int n) {
    long a = 0;
    long b = 0;
    long c = 0;
    long r = 0;
    r = 1;
    for (int i = 1; i <= n; i++) {
      c = b;
      b = a;
      a = r;
      r = a + b + c;
    }
    return r;
  }

  private static long countWaysRecursive(int n) {
    if (n == 0) {
      return 1;
    } else if (n < 0) {
      return 0;
    }
    return countWaysRecursive(n - 1) + countWaysRecursive(n - 2) + countWaysRecursive(n - 3);
  }
}
