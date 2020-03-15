package DynamicProgramming;

import java.util.Arrays;

public class LPS {
  public static void main(String[] args) {
    String str = "hackerrekcahba";
    int n = str.length();
    int[][] lps = new int[n][n];
    lpsUtils(lps, str, n);
    for (int[] l : lps)
      System.err.println(Arrays.toString(l));
    System.out.println(lps[0][n - 1]);
    printLPS(lps, str);
  }

  private static void lpsUtils(int[][] lps, String str, int n) {
    for (int i = 0; i < n; i++) {
      lps[i][i] = 1;
    }

    for (int j = 1; j < n; j++) {
      int cl = j;
      for (int i = 0; i < n - j; i++) {
        if (str.charAt(i) == str.charAt(cl)) {
          lps[i][cl] = 2 + lps[i + 1][cl - 1];
        } else {
          lps[i][cl] = Math.max(lps[i][cl - 1], lps[i + 1][cl]);
        }
        cl = cl + 1;
      }
    }
  }

  private static void printLPS(int[][] lps, String str) {
    int n = lps.length;
    int val = lps[0][n - 1];
    int i = 0;
    int j = n - 1;
    String s = "";
    while (val >= 1) {
      if (lps[i][j - 1] == val) {
        j = j - 1;
      } else if (lps[i + 1][j] == val) {
        i = i + 1;
      } else {
        s += str.charAt(j);
        i = i + 1;
        j = j - 1;
        val = lps[i][j];

      }
    }
    s = s + rev(s);
    System.out.println(s);
  }

  private static String rev(String s) {
    String r = "";
    int n = s.length() - 1;
    for (int i = n; i >= 0; i--) {
      r += s.charAt(i);
    }
    return r;
  }
}
