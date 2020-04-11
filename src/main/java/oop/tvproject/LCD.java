package oop.tvproject;

public class LCD extends TV {
  boolean FM;

  public LCD(int size, String type, boolean FM) {
    super(size, type);
    this.FM = FM;
  }

  public void print() {
    System.out.println("LCD{" + "FM=" + FM + ", size=" + size + ", type='" + type + '\'' + '}');
  }

  public boolean isFM() {
    return this.FM;
  }
}
