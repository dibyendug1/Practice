package oracle.vending;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VendingMachineTest {
  @Test public void testBuyItemWithExactPrice() {
    VendingMachine vm = VendingMachineFactory.createVendingMachine();
    //select item
    long price = vm.selectItemAndGetPrice(Item.ITEM1);
    //price should be item1's price
    assertEquals(Item.ITEM1.getPrice(), price);
    //amount 23 paid
    vm.insertCoin(10);
    vm.insertCoin(5);
    vm.insertCoin(5);
    vm.insertCoin(3);

    Bucket<Item, List<Integer>> bucket = vm.collectItemAndChange();
    Item item = bucket.getFirst();
    List<Integer> change = bucket.getSecond();

    //should be item1
    assertEquals(Item.ITEM1, item);
    //there should not be any change
    assertTrue(change.isEmpty());
  }

  @Test public void testBuyItemWithMorePrice() {
    VendingMachine vm = VendingMachineFactory.createVendingMachine();

    long price = vm.selectItemAndGetPrice(Item.ITEM1);
    assertEquals(Item.ITEM1.getPrice(), price);

    vm.insertCoin(100);

    Bucket<Item, List<Integer>> bucket = vm.collectItemAndChange();
    Item item = bucket.getFirst();
    List<Integer> change = bucket.getSecond();

    //should be item1
    assertEquals(Item.ITEM1, item);
    //there should be change
    assertTrue(!change.isEmpty());
    //comparing change
    assertEquals(100 - Item.ITEM1.getPrice(), getTotal(change));

  }

  @Test public void testBuyItemWithLessPrice() {
    VendingMachine vm = VendingMachineFactory.createVendingMachine();

    long price = vm.selectItemAndGetPrice(Item.ITEM1);
    assertEquals(Item.ITEM1.getPrice(), price);

    vm.insertCoin(10);

    Bucket<Item, List<Integer>> bucket = vm.collectItemAndChange();
    Item item = bucket.getFirst();
    List<Integer> change = bucket.getSecond();

    //should be null
    assertEquals(null, item);
    //there should be change
    assertTrue(!change.isEmpty());
    //change should be equal to inserted amount
    assertEquals(10, getTotal(change));
  }

  @Test(expected = NotSufficientChangeException.class) public void testMinCoinsException() {
    VendingMachine vm = VendingMachineFactory.createVendingMachine();

    for (int i = 0; i < 5; i++) {
      // Min coins > 4 will throw exception
      vm.selectItemAndGetPrice(Item.ITEM2);
      vm.insertCoin(100);
      vm.insertCoin(100);
      vm.insertCoin(100);
      vm.insertCoin(100);
      vm.insertCoin(100);
      vm.insertCoin(100);
      vm.insertCoin(100);
      vm.insertCoin(100);
      vm.collectItemAndChange();
    }
  }

  @Test(expected = SoldOutException.class) public void testReset() {
    VendingMachine vmachine = VendingMachineFactory.createVendingMachine();
    vmachine.reset();

    vmachine.selectItemAndGetPrice(Item.ITEM3);

  }

  private long getTotal(List<Integer> change) {
    long total = 0;
    for (Integer c : change) {
      total = total + c;
    }
    return total;
  }
}