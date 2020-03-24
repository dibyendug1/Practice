package DynamicProgramming;

public class LCS {
  public static void lcs(String str1, String str2) {
    int m = str1.length();
    int n = str2.length();
    int max = Integer.MIN_VALUE;
    int[][] lcs = new int[m + 1][n + 1];
    for (int i = 0; i < m + 1; i++) {
      for (int j = 0; j < n + 1; j++) {
        if (i == 0 || j == 0) {
          lcs[i][j] = 0;
        }
      }
    }

    for (int i = 1; i < m + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          lcs[i][j] = lcs[i - 1][j - 1] + 1;
        } else {
          lcs[i][j] = max(lcs[i - 1][j], lcs[i][j - 1]);
        }
        max = (max < lcs[i][j]) ? lcs[i][j] : max;
      }
    }
    System.out.println("LCS size: " + max);
    printLCS(lcs, m, n, str1);
  }

  private static void printLCS(int[][] lcs, int m, int n, String str1) {
    System.out.println("LCS is:");
    String lcsStr = "";
    int max = Integer.MIN_VALUE;
    int mi = 1, ni = 1;
    for (int i = 1; i < m + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        if (max < lcs[i][j]) {
          max = lcs[i][j];
          mi = i;
          ni = j;
        }
      }
    }
    int val = max;
    while (val != 0) {
      if (val == lcs[mi - 1][ni]) {
        mi = mi - 1;
      } else if (val == lcs[mi][ni - 1]) {
        ni = ni - 1;
      } else {
        lcsStr += str1.charAt(mi - 1);
        mi = mi - 1;
        ni = ni - 1;
        val = lcs[mi][ni];
      }
    }
    for (int i = lcsStr.length() - 1; i >= 0; i--) {
      System.out.print(lcsStr.charAt(i));
    }
  }

  private static int max(int n1, int n2) {
    return n1 > n2 ? n1 : n2;
  }

  public static void main(String[] args) {
    String str1 = "GXTXAYB";
    String str2 = "AGGTAB";
    lcs(str1, str2);
  }
}
