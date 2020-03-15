package DynamicProgramming;

import java.util.Scanner;

public class LPSubstring {
  private static class LpsObj {
    Integer max;
    Integer s;
    Integer e;

    private LpsObj(int n) {
      max = Integer.MIN_VALUE;
      s = 0;
      e = n - 1;
    }

    @Override public String toString() {
      return "LpsObj{" + "max=" + max + ", s=" + s + ", e=" + e + '}';
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine();

    LpsObj lpsObj = new LpsObj(str.length());
    lps(str, lpsObj);
    //System.out.println(lpsObj);
    System.out.println(str.substring(lpsObj.s, lpsObj.e));
    //System.out.println(isPallindrome(str));
  }

  private static void lps(String str, LpsObj lpsObj) {
    int n = str.length();
    String[][] lpstr = new String[n][n];
    String maxStr = "";
    int i = 0;
    int j = n -1;
    for (int c = 0; c < n; c++) {
      if (isPallindrome(str.substring(i, j)) && lpsObj.max < lpstr[i][j].length()) {
        lpsObj.max = lpstr[i][j].length();
        lpsObj.s = i;
        lpsObj.e = j;
      } else {

      }

      /*for (int j = i + 1; j < n; j++) {
        lpstr[i][j] = str.substring(i, j);
        if (isPallindrome(lpstr[i][j]) && lpsObj.max < lpstr[i][j].length()) {
          lpsObj.max = lpstr[i][j].length();
          lpsObj.s = i;
          lpsObj.e = j;
        }
      }*/
    }
  }

  private static boolean isPallindrome(String str) {
    int n = str.length();
    for (int i = 0; i < n / 2 + 1; i++) {
      if (str.charAt(i) != str.charAt(n - i - 1)) {
        return false;
      }
    }
    return true;
  }
}
