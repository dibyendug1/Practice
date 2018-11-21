package DynamicProgramming;

import javax.sound.midi.Soundbank;

public class MathProblem {
  public static void main(String[] args) {
    int n = 9;
    System.out.printf("factorial of %d is %d\n", n, factorial(n));
    System.out.println("\nFibbonacci series of " + n);
    long start = System.nanoTime();
    for (int i = 0; i <= n; i++) {
      System.out.print(fib(i) + " ");
    }
    long end = System.nanoTime();
    System.out.println("\n" + (end - start));
    start = System.nanoTime();
    System.out.println("\nNon recursive fibbonacci series of " + n);
    nonRecurFib(n);
    end = System.nanoTime();
    System.out.println("\n" + (end - start));
  }

  private static void nonRecurFib(int n) {
    int[] f = new int[n + 1];
    f[0] = 0;
    f[1] = 1;
    for (int i = 0; i <= n; i++) {
      if (i > 1) {
        f[i] = f[i - 1] + f[i - 2];
      }
      System.out.print(f[i] + " ");

    }
  }

  private static int fib(int i) {
    if (i == 0) {
      return 0;
    }
    if (i == 1) {
      return 1;
    }

    return fib(i - 1) + fib(i - 2);

  }

  private static int factorial(int n) {
    if (n == 0 || n == 1) {
      return 1;
    }
    return n * factorial(n - 1);
  }
}
