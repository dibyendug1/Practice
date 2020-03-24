package DynamicProgramming;

import java.util.Arrays;

public class MinCostPath {
  public static void main(String[] args) {
    int[][] cost = { { 1, 2, 3 },
                     { 4, 8, 2 },
                     { 1, 5, 3 } };

    int[][] cost2 =
        { { 31, 100, 65, 12, 18 },
          { 10, 13, 47, 157, 6 },
          { 100, 113, 174, 11, 33 },
          { 88, 124, 41, 20, 140 },
          { 99, 32, 111, 41, 20 } };
    int m = 2;
    int n = 2;
    minCost(cost, m, n);
  }

  private static void minCost(int[][] cost, int m, int n) {
    {
      int[][] path = new int[cost.length][cost[0].length];
      int[][][] trace = new int[cost.length][cost[0].length][2];
      for (int i = 0; i < cost.length; i++) {
        for (int j = 0; j < cost[0].length; j++) {
          if (i == 0 || j == 0) {
            path[i][j] = cost[i][j];
          }
        }
      }

      for (int i = 0; i < cost.length; i++) {
        for (int j = 0; j < cost.length; j++) {
          if (i == 0 && j == 0) {
            path[i][j] = cost[i][j];
            trace[i][j][0] = i;
            trace[i][j][1] = j;
          } else if (i == 0) {
            path[i][j] =
                cost[i][j] + minValue(Integer.MAX_VALUE, path[i][j - 1],
                    Integer.MAX_VALUE);
            trace[i][j][0] = i;
            trace[i][j][1] = j - 1;
          } else if (j == 0) {
            path[i][j] =
                cost[i][j] + minValue(path[i - 1][j], Integer.MAX_VALUE,
                    Integer.MAX_VALUE);
            trace[i][j][0] = i - 1;
            trace[i][j][1] = j;
          } else {
            path[i][j] = cost[i][j] + minValue(path[i - 1][j], path[i][j - 1],
                path[i - 1][j - 1]);
            switch (minValueIndex(path[i - 1][j], path[i][j - 1],
                path[i - 1][j - 1])) {
            case 1:
              trace[i][j][0] = i - 1;
              trace[i][j][1] = j;
              break;
            case 2:
              trace[i][j][0] = i;
              trace[i][j][1] = j - 1;
              break;
            case 3:
              trace[i][j][0] = i - 1;
              trace[i][j][1] = j - 1;
              break;
            }

          }
        }
      }

      for (int i = 0; i < path.length; i++) {
        System.out.println(Arrays.toString(path[i]));
      }

      System.out.printf("\nPath to (%d,%d):\n", m, n);
      printPath(trace, m, n);
    }
  }

  private static void printPath(int[][][] trace, int m, int n) {
    if (m == 0 && n == 0) {
      System.out.printf("<%d,%d>\n", m, n);
      return;
    }
    printPath(trace, trace[m][n][0], trace[m][n][1]);
    System.out.printf("<%d,%d>\n", m, n);
  }

  private static int minValue(int i, int i1, int i2) {
    return (i < i1) ? (i < i2 ? i : i2) : (i1 < i2 ? i1 : i2);
  }

  private static int minValueIndex(int i, int i1, int i2) {
    return (i < i1) ? (i < i2 ? 1 : 3) : (i1 < i2 ? 2 : 3);
  }
}
