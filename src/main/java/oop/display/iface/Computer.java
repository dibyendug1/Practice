package oop.display.iface;

public class Computer {
  DisplayModule dm;

  protected void setDisplayModule(DisplayModule dm) {
    this.dm = dm;
  }

  public void display() {
    dm.display();
  }
}
