package DynamicProgramming;

public class EggDropping {
  public static void main(String[] args) {
    int n = 2; //Number of eggs
    int k = 4; //Floors
    int[][] table = new int[n + 1][k + 1];
    int res = 0;
    // Find worst case minimum attempts
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= k; j++) {
        eggDrop(table, i, j);

      }
    }
    System.out.println("Minimum attempts :" + table[n][k]);
  }

  private static void eggDrop(int[][] table, int n, int k) {
    if (n == 0) {
      table[n][k] = n;
      return;
    }
    if (k == 0 || k == 1) {
      table[n][k] = k;
      return;
    }
    if (n == 1) {
      table[n][k] = k;
      return;
    }

    int min = Integer.MAX_VALUE;
    for (int x = 1; x <= k; x++) {
      int res = 1 + Math.max(table[n - 1][x - 1], table[n][k - x]);
      if (min > res) {
        min = res;
      }
    }
    table[n][k] = min;
    return;
  }
}
