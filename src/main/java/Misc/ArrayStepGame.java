package Misc;

import java.util.Scanner;

public class ArrayStepGame {

  public static boolean canWin(int leap, int[] game) {
    // Return true if you can win the game; otherwise, return false.
    return isEnd(0, game, leap);
  }

  public static boolean isEnd(int n, int[] game, int leap) {
    if (n < 0) {
      return false;
    }
    if (n > game.length - 1) {
      return true;
    }
    if (n == game.length - 1) {
      if (game[n] == 0) {
        return true;
      }
      return false;
    }

    if (game[n] != 0) {
      return false;
    }
    game[n] = 1;
    return isEnd(n - 1, game, leap) || isEnd(n + 1, game, leap) || isEnd(
        n + leap, game, leap);
  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int q = scan.nextInt();
    while (q-- > 0) {
      int n = scan.nextInt();
      int leap = scan.nextInt();

      int[] game = new int[n];
      for (int i = 0; i < n; i++) {
        game[i] = scan.nextInt();
      }

      System.out.println((canWin(leap, game)) ? "YES" : "NO");
    }
    scan.close();
  }
}