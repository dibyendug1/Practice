package DynamicProgramming;

public class LongestPallindromicSubstring {
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
    String str = "qdhcmtrrgntcenvilbnwdttewaeeepiihouihuxccjwmlmrdjaigerwwpxdvqgtseienkywkuhburikdseinyzujbmuogieeigousmbjuzyniesdkirubhukwykneiestgqmvdxpwwrcegiajdrmlmwjccxuiuohiipeeeawettdwblinectngriprtmchdq";
    int n = str.length();

    LpsObj lpsObj = new LpsObj(n);
    lps(0, n - 1, str, lpsObj);
    System.out.println(lpsObj);
    System.out.println(str.substring(lpsObj.s, lpsObj.e));
    //System.out.println(isPallindrome(str));
  }

  private static void lps(int i, int j, String str, LpsObj lpsObj) {
    if (isPallindrome(str.substring(i, j))) {
      if (j - i + 1 > lpsObj.max) {
        lpsObj.max = j - i;
        lpsObj.s = i;
        lpsObj.e = j;
      }
    } else {
      lps(i + 1, j, str, lpsObj);
      lps(i, j - 1, str, lpsObj);
    }

  }

  private static boolean isPallindrome(String str) {
    int n = str.length();
    for (int i = 0; i < n; i++) {
      if (str.charAt(i) != str.charAt(n - i - 1)) {
        return false;
      }
    }
    return true;
  }
}
