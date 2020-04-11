package oop.display.iface;

public class Projector implements DisplayModule {
  @Override
  public void display() {
    System.out.println("Displaying through Projector.");
  }
}
