package oop.tvproject;

import java.math.BigInteger;

public class CRT extends TV {
  //int size = 100;
  boolean bluetooth;

  public CRT(int size, String type, boolean bluetooth) {
    super();
    //this.size = size;
    this.bluetooth = bluetooth;
  }

  public void print() {
    System.out.println("CRT{" + "bluetooth=" + bluetooth + ", size=" + size + ", type='" + type + '\'' + '}');
  }

  public boolean isBluetooth() {
    return this.bluetooth;
  }
}
