/**
 * There is a vending machine which allows a user to select a product and make payment by placing coins in a coin
 * container and then pushing a button. When the button is pushed, if the inserted coins are sufficient to cover the purchase price of the product, the product is dispensed and change is given. Otherwise, the inserted coins are returned to the user.
 * (a)  You need to provide an object oriented design of the vending machine, identify all classes and their functionality.
 * (b)  Vending machine works with following set of denominations of coins: 1, 3, 4, 5, 10, 30, 40, 50 and 100. You need to provide an algorithm and its implementation in your preferred programming language to return change with NOT more than four coins (MINIMUM number of coins).
 * <p>
 * Sample Input:
 * a)  Selected product price – 23
 * b)  Payment received – 100 Rs coin
 */

package oracle.vending;

import java.util.List;

public interface VendingMachine {
  public int selectItemAndGetPrice(Item item);

  public void insertCoin(int coin);

  public Bucket<Item, List<Integer>> collectItemAndChange();

  public void reset();

  public void printStats();
}
