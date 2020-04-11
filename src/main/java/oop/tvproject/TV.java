package oop.tvproject;

public class TV {
  int size;
  String type;

  public TV() {
    this.size = 21;
    this.type = "Default";
  }

  public TV(int size, String type) {
    this.size = size;
    this.type = type;
  }

  public void print() {
    System.out.println("TV{" + "size=" + size + ", type='" + type + '\'' + '}');
  }
}
