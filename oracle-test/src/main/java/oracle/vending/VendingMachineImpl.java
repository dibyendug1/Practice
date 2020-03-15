package oracle.vending;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineImpl implements VendingMachine {
  private Inventory<Integer> cashInventory = new Inventory<Integer>();
  private Inventory<Item> itemInventory = new Inventory<Item>();
  private int totalSales;
  private Item currentItem;
  private int currentBalance;
  private List<Integer> insertedCoins = new ArrayList<>();
  private final int MAX_NUM_COIN = 4;

  public VendingMachineImpl() {
    initialize();
  }

  private void initialize() {
    //initialize machine with 5 coins of each denomination
    //and 5 cans of each Item
    for (int c : Coin.getCoins()) {
      cashInventory.put(c, 5);
    }

    for (Item i : Item.values()) {
      itemInventory.put(i, 5);
    }

  }

  @Override public int selectItemAndGetPrice(Item item) {
    if (itemInventory.hasItem(item)) {
      currentItem = item;
      return currentItem.getPrice();
    }
    throw new SoldOutException("Sold Out, Please buy another item");
  }

  @Override public void insertCoin(int coin) {
    currentBalance = currentBalance + coin;
    cashInventory.add(coin);
    insertedCoins.add(coin);
  }

  @Override public Bucket<Item, List<Integer>> collectItemAndChange() {
    Item item = collectItem();
    if (item == null) {
      return new Bucket<Item, List<Integer>>(item, insertedCoins);
    }

    totalSales = totalSales + currentItem.getPrice();

    List<Integer> change = collectChange();

    return new Bucket<Item, List<Integer>>(item, change);
  }

  private Item collectItem() throws NotSufficientChangeException {
    if (isFullPaid()) {
      if (hasSufficientChange()) {
        itemInventory.deduct(currentItem);
        return currentItem;
      }
      throw new NotSufficientChangeException("Not Sufficient change in  Inventory");
    }
    return null;
  }

  private List<Integer> collectChange() {
    int changeAmount = currentBalance - currentItem.getPrice();
    List<Integer> change = getChange(changeAmount);
    updateCashInventory(change);
    currentBalance = 0;
    currentItem = null;
    return change;
  }

  private boolean isFullPaid() {
    if (currentBalance >= currentItem.getPrice()) {
      return true;
    }
    return false;
  }

  private List<Integer> getChange(int amount) throws NotSufficientChangeException {
    int[] coins = Coin.getCoins();
    int[] changeCoinCount = new int[amount + 1]; // To count how many coins need to return
    int[] changeCoins = new int[amount + 1]; // coins to return
    changeCoinCount[0] = 0;
    for (int i = 1; i <= amount; i++) {
      changeCoinCount[i] = Integer.MAX_VALUE;
      changeCoins[i] = -1;
    }

    for (int j = 0; j < coins.length; j++) {
      for (int i = 1; i <= amount; i++) {
        if (i >= coins[j]) {
          if (changeCoinCount[i - coins[j]] + 1 < changeCoinCount[i] && cashInventory.hasItem(coins[j])) {
            changeCoinCount[i] = 1 + changeCoinCount[i - coins[j]];
            changeCoins[i] = j;
          }
        }
      }
    }

    if (changeCoinCount[amount] > MAX_NUM_COIN || changeCoinCount[changeCoinCount.length - 1] == -1) {
      throw new NotSufficientChangeException("NotSufficientChange, Please try another product ");
    }

    return generateCoinsList(changeCoins);
  }

  private List<Integer> generateCoinsList(int[] changeCoins) {
    List<Integer> returnCoins = new ArrayList<>();
    int start = changeCoins.length - 1;
    while (start != 0) {
      int j = changeCoins[start];
      returnCoins.add(Coin.getCoins()[j]);
      start = start - Coin.getCoins()[j];
    }
    return returnCoins;
  }

  @Override public void reset() {
    cashInventory.clear();
    itemInventory.clear();
    totalSales = 0;
    currentItem = null;
    currentBalance = 0;
  }

  @Override public void printStats() {
    System.out.println("Total Sales : " + totalSales);
    System.out.println("Current Item Inventory : " + itemInventory);
    System.out.println("Current Cash Inventory : " + cashInventory);
  }

  private boolean hasSufficientChange() {
    return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
  }

  private boolean hasSufficientChangeForAmount(int amount) {
    try {
      getChange(amount);
    } catch (NotSufficientChangeException nsce) {
      return false;
    }
    return true;
  }

  private void updateCashInventory(List<Integer> change) {
    for (Integer c : change) {
      cashInventory.deduct(c);
    }
  }

  public long getTotalSales() {
    return totalSales;
  }

}