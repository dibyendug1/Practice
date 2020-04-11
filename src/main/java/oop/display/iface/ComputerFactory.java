package oop.display.iface;

public class ComputerFactory {
  public static Computer getComputer(String display) {
    Computer computer = new Computer();
    DisplayModule dm;
    switch (display.toUpperCase()) {
      case "TV":
        dm = new TVDisplay();
        computer.setDisplayModule(dm);
        break;
      case "PROJECTOR":
        dm = new Projector();
        computer.setDisplayModule(dm);
        break;
      case "MONITOR":
        dm = new Monitor();
        computer.setDisplayModule(dm);
        break;
    }
    return computer;
  }
}
