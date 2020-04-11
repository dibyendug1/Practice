package oop.display.iface;

public class TVDisplay implements DisplayModule {
  @Override
  public void display() {
    System.out.println("Displaying through TV.");
  }
}
