package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class PrimeSubsetSum {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int q = sc.nextInt();
    int n = sc.nextInt();
    int pivot = sc.nextInt();
    List<Integer> primeNumbers = getPrimes(n);
    //int pivot = 4;
    //List<Integer> inputs = new ArrayList<>(Arrays.asList(1, 2, 3));
    List<List<Integer>> allSubSets = getAllSubsets(primeNumbers);
    System.out.println(allSubSets);
    AtomicInteger count = new AtomicInteger();
    AtomicInteger count2 = new AtomicInteger();
    allSubSets.forEach(subset -> {
      if (!subset.isEmpty() && isPrime(sumList(subset, count2, pivot))) {
        count.incrementAndGet();
      }
    });
    System.out.println(String.format("%d %d", (count.get() % new Double(Math.pow(10, 16)).intValue()),
                                     (count2.get() % new Double(Math.pow(10, 16)).intValue())));
  }

  private static List<Integer> getPrimes(int n) {
    List<Integer> primes = new ArrayList<>();
    if (n <= 3 && n > 1) {
      for (int i = 1; i <= n; i++) {
        primes.add(i);
      }
    }
    if (n > 3) {
      for (int i = 2; i <= 3; i++) {
        primes.add(i);
      }
      for (int i = 5; i <= n; i++) {
        if (isPrime(i)) {
          primes.add(i);
        }
      }
    }
    return primes;
  }

  private static List<List<Integer>> getAllSubsets(List<Integer> inputs) {
    if (inputs == null || inputs.isEmpty()) {
      return new ArrayList<>();
    }
    List<List<Integer>> allSubSets = new ArrayList<>();
    allSubSets.add(new ArrayList<>());
    for (int e : inputs) {
      List<List<Integer>> moreSets = new ArrayList<>();
      for (List<Integer> sub : allSubSets) {
        List<Integer> newSub = new ArrayList<>();
        newSub.addAll(sub);
        newSub.add(e);
        moreSets.add(newSub);
      }
      allSubSets.addAll(moreSets);
    }
    return allSubSets;
  }

  private static long sumList(List<Integer> inputs, AtomicInteger count2, int pivot) {
    AtomicLong sum = new AtomicLong();
    AtomicBoolean flag = new AtomicBoolean(true);
    inputs.forEach(i -> {
      sum.addAndGet(i);
      if (i >= pivot) {
        flag.set(false);
      }
    });
    if (flag.get()) {
      count2.incrementAndGet();
    }
    return sum.get();
  }

  private static boolean isPrime(long n) {
    //System.out.println("Check is prime: " + n);
    if (n == 1) {
      return true;
    }
    if (n == 0) {
      return false;
    }
    for (long i = 2; i < (n / 2 + 1); i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
}
