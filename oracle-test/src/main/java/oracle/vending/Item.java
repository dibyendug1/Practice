package oracle.vending;

public enum Item {
  // Sample items
  ITEM1("item1", 23), ITEM2("item2", 35), ITEM3("item3", 45);

  private String name;
  private int price;

  private Item(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }
}