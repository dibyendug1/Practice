package oracle;

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

/*There is a vending machine which allows a user to select a product and make payment by placing coins in a coin container and then pushing a button. When the button is pushed, if the inserted coins are sufficient to cover the purchase price of the product, the product is dispensed and change is given. Otherwise, the inserted coins are returned to the user.
(a)  You need to provide an object oriented design of the vending machine, identify all classes and their functionality.
(b)  Vending machine works with following set of denominations of coins: 1, 3, 4, 5, 10, 30, 40, 50 and 100. You need to provide an algorithm and its implementation in your preferred programming language to return change with NOT more than four coins (MINIMUM number of coins).

Sample Input:
a)  Selected product price – 23
b)  Payment received – 100 Rs coin*/

class Solution {
  public static void main(String[] args) {
    int productPrice = 50;
    int[] coins = { 1, 3, 4, 5, 10, 30, 40, 50, 100 };
    int payment = 100;

    printChangeGiven(coins, productPrice, payment);
  }

  public static void printChangeGiven(int[] coins, int productPrice, int payment) {
    int change = payment - productPrice;
    int[] changeCoinCount = new int[change + 1]; // To count how many coins need to return
    int[] changeCoins = new int[change + 1]; // coins to return
    changeCoinCount[0] = 0;
    for (int i = 1; i <= change; i++) {
      changeCoinCount[i] = Integer.MAX_VALUE;
      changeCoins[i] = -1;
    }

    for (int j = 0; j < coins.length; j++) {
      for (int i = 1; i <= change; i++) {
        if (i >= coins[j]) {
          if (changeCoinCount[i - coins[j]] + 1 < changeCoinCount[i]) {
            changeCoinCount[i] = 1 + changeCoinCount[i - coins[j]];
            changeCoins[i] = j;
          }
        }
      }
    }
    printCoins(changeCoins, coins);
  }

  private static void printCoins(int changeCoinCount[], int coins[]) {
    if (changeCoinCount[changeCoinCount.length - 1] == -1) {
      System.out.print("No solution is possible");
      return;
    }
    int start = changeCoinCount.length - 1;
    System.out.print("Coins used :");
    while (start != 0) {
      int j = changeCoinCount[start];
      System.out.print(coins[j] + " ");
      start = start - coins[j];
    }
    System.out.print("\n");
  }
}
