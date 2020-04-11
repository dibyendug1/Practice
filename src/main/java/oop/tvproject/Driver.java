package oop.tvproject;

public class Driver {
  public static void main(String[] args) {
    TV crt = new CRT(32,"CRT", false);
    TV lcd = new LCD(43,"LCD", true);

    crt.print();
    lcd.print();


  }
}
