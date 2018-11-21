package Misc;

public class OddEvenThread implements Runnable {

  private static int counter = 1;
  private static final Object lock = new Object();

  public static void main(String[] args) {
    Thread t1 = new Thread(new OddEvenThread(), "odd");
    t1.start();
    Thread t2 = new Thread(new OddEvenThread(), "even");
    t2.start();
  }

  @Override
  public void run() {
    while (counter <= 100) {
      synchronized (lock) {
        if (counter % 2 == 0) {
          System.out.println(
              counter + " Written By even Thread-" + Thread.currentThread()
                  .getName());
          counter++;
          try {
            lock.notify();
            lock.wait();

          } catch (InterruptedException e) {
            e.printStackTrace();
          }

        } else if (counter % 2 == 1) {
          System.out.println(
              counter + " Written By odd Thread-" + Thread.currentThread()
                  .getName());
          counter++;

          try {
            lock.notify();
            lock.wait();

          } catch (InterruptedException e) {
            e.printStackTrace();
          }

        }
      }
    }
  }
}