package oop.display.iface;

public class Monitor implements DisplayModule {
  @Override
  public void display() {
    System.out.println("Displaying through Monitor.");
  }
}
