package DynamicProgramming;

import java.util.ArrayList;

/**
 * Permutations of abc
 * abc, acb, bca, bac, cab, cba
 */
public class GeneratePermutations {
  public static void main(String[] args) {
    String str = "aab";
    int n = str.length();
    int numOfPermutations = factorial(n);
    System.out.println("Number of permutations: " + numOfPermutations);
    ArrayList<String> permutations = getPermutations(str);
    System.out.println(permutations.toString());
  }

  private static ArrayList<String> getPermutations(String str) {
    if (str.length() == 0) {
      return new ArrayList<>();
    }

    ArrayList<String> allStrings = new ArrayList<>();
    //ArrayList<String> result = new ArrayList<>();
    allStrings.add("");
    for (int i = 0; i < str.length(); i++) {
      String s = str.charAt(i) + "";
      ArrayList<String> temp = new ArrayList<>();
      for (String e : allStrings) {
        for (int j = 0; j <= e.length(); j++) {
          temp.add(insertChar(s, e, j));
        }

      }
      allStrings = temp;
    }
    return allStrings;
  }

  private static String insertChar(String s, String e, int j) {
    String start = e.substring(0, j);
    String end = e.substring(j);
    return start + s + end;
  }

  private static int factorial(int n) {
    if (n == 0 || n == 1) {
      return 1;
    }
    int fact = 1;
    for (int i = 1; i <= n; i++) {
      fact *= i;
    }
    return fact;
  }
}
