package oop.display.iface;

public class Main {
  public static void main(String[] args) {
    Computer computer;
    computer = ComputerFactory.getComputer("TV");
    computer.display();

    computer = ComputerFactory.getComputer("Monitor");
    computer.display();

    computer = ComputerFactory.getComputer("Projector");
    computer.display();

  }
}
