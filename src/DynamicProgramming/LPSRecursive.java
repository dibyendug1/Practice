package DynamicProgramming;

public class LPSRecursive {
  public static void main(String[] args) {
    String str = "BAAQWQABlCAC";
    int i = 0;
    int j = str.length() - 1;
    int res = lps(str, i, j);
    System.out.println(res);
  }

  private static int lps(String str, int i, int j) {
    if (i == j) {
      return 1;
    }
    if (i > j) {
      return 0;
    }

    if (str.charAt(i) == str.charAt(j)) {
      return 2 + lps(str, i + 1, j - 1);
    } else {
      return Math.max(lps(str, i, j - 1), lps(str, i + 1, j));
    }
  }
}
