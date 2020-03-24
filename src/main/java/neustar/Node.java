package neustar;

/**
 * Data structre ti store the detais of categories/products
 */
public class Node {
  int id;
  String name;
  double originalPrice = -1;
  double sellingPrice;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getOriginalPrice() {
    return originalPrice;
  }

  public void setOriginalPrice(double originalPrice) {
    this.originalPrice = originalPrice;
  }

  public double getSellingPrice() {
    return sellingPrice;
  }

  public void setSellingPrice(double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }
}
