package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class LISCount {

  // Complete the getCountOfPossibleTeams function below.
  static int getCountOfPossibleTeams(List<Integer> coders) {
    return lisCount(coders) + ldsCount(coders);
  }

  static int ldsCount(List<Integer> coders) {
    int k = 3;
    int n = coders.size();
    int dp[][] = new int[k][n], sum = 0;
    // count of increasing subsequences of size 1
    // ending at each arr[i]
    for (int i = 0; i < n; i++) {
      dp[0][i] = 1;
    }

    // building up the matrix dp[][]
    // Here 'l' signifies the size of
    // increassing subsequence of size (l+1).
    for (int l = 1; l < k; l++) {
      // for each increasing subsequence of size 'l'
      // ending with element arr[i]
      for (int i = l; i < n; i++) {
        // count of increasing subsequences of size 'l'
        // ending with element arr[i]
        dp[l][i] = 0;
        for (int j = l - 1; j < i; j++) {
          if (coders.get(j) > coders.get(i)) {
            dp[l][i] += dp[l - 1][j];
          }
        }
      }
    }

    // sum up the count of increasing subsequences of
    // size 'k' ending at each element arr[i]
    for (int i = k - 1; i < n; i++) {
      sum += dp[k - 1][i];
    }

    // required number of increasing
    // subsequences of size k
    return sum;
  }

  static int lisCount(List<Integer> coders) {
    int k = 3;
    int n = coders.size();
    int dp[][] = new int[k][n], sum = 0;
    // count of increasing subsequences of size 1
    // ending at each arr[i]
    for (int i = 0; i < n; i++) {
      dp[0][i] = 1;
    }

    // building up the matrix dp[][]
    // Here 'l' signifies the size of
    // increassing subsequence of size (l+1).
    for (int l = 1; l < k; l++) {
      // for each increasing subsequence of size 'l'
      // ending with element arr[i]
      for (int i = l; i < n; i++) {
        // count of increasing subsequences of size 'l'
        // ending with element arr[i]
        dp[l][i] = 0;
        for (int j = l - 1; j < i; j++) {
          if (coders.get(j) < coders.get(i)) {
            dp[l][i] += dp[l - 1][j];
          }
        }
      }
    }

    // sum up the count of increasing subsequences of
    // size 'k' ending at each element arr[i]
    for (int i = k - 1; i < n; i++) {
      sum += dp[k - 1][i];
    }

    // required number of increasing
    // subsequences of size k
    return sum;
  }

  public static void main(String[] args) {
    List<Integer> coders = new ArrayList<Integer>();
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    for (int i = 1; i < coders.size(); i++) {
      int maxLeft = Integer.MIN_VALUE, maxRight = Integer.MIN_VALUE;
      int minLeft = Integer.MAX_VALUE, minRight = Integer.MAX_VALUE;
      // choosing a coder from the left of the current ith coder
      for (int j = 0; j < i; j++) {
        if (coders.get(j) < coders.get(i))
          minLeft = Math.min(coders.get(j), minLeft);
        else
          maxLeft = Math.max(coders.get(j), maxLeft);
      }
      // choosing a coder from the right of the current ith coder
      for (int j = i + 1; j < coders.size(); j++) {

        if (coders.get(j) < coders.get(i))
          minRight = Math.min(coders.get(j), minRight);
        else
          maxRight = Math.max(coders.get(j), maxRight);
      }
      // only if there is a increasing subsequence present
      if (minLeft != Integer.MAX_VALUE && maxRight != Integer.MIN_VALUE) {
        List<Integer> ans = new ArrayList<Integer>();
        ans.add(minLeft);
        ans.add(coders.get(i));
        ans.add(maxRight);
        res.add(ans);

      }
      // only if there is a decreasing subsequence present
      if (minRight != Integer.MAX_VALUE && maxLeft != Integer.MIN_VALUE) {
        List<Integer> ans = new ArrayList<Integer>();
        ans.add(maxLeft);
        ans.add(coders.get(i));
        ans.add(minRight);
        res.add(ans);
      }
    }

    System.out.println(res.size());
  }
}
