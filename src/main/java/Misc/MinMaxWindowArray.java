package Misc;

import java.util.Deque;
import java.util.LinkedList;

//Sum of diff of minimum and maximum elements of all subarrays of size k.
public class MinMaxWindowArray {
  public static void main(String[] args) {
    int[] arr = { 2, 5, 1, 7, 3, 1, 2 }; // 6 + 6 + 6 + 6
    int k = 4;
    int sumOfDiff = DiffOfKsubArray(arr, k);
    System.out.println(sumOfDiff);
  }

  private static int DiffOfKsubArray(int[] arr, int k) {
    Deque<Integer> minQueue = new LinkedList<>(); // Deque to Store min elements in the window
    Deque<Integer> maxQueue = new LinkedList<>(); // Deque to Store max elements in the window
    int sum = 0;
    int i = 0;
    for (; i < k; i++) {
      // If lst element in the queue is greater than current value, remove it
      while (!minQueue.isEmpty() && arr[minQueue.getLast()] > arr[i]) {
        minQueue.removeLast();
      }
      // If lst element in the queue is less than current value, remove it
      while (!maxQueue.isEmpty() && arr[maxQueue.getLast()] < arr[i]) {
        maxQueue.removeLast();
      }
      minQueue.add(i);
      maxQueue.add(i);
    }

    sum = sum + arr[maxQueue.getFirst()] - arr[minQueue.getFirst()];
    for (; i < arr.length; i++) {
      // if first element is out of window boundary, remove it
      while (!minQueue.isEmpty() && minQueue.getFirst() <= (i - k)) {
        minQueue.removeFirst();
      }
      // if first element is out of window boundary, remove it
      while (!maxQueue.isEmpty() && maxQueue.getFirst() <= (i - k)) {
        maxQueue.removeFirst();
      }
      // If lst element in the queue is greater than current value, remove it
      while (!minQueue.isEmpty() && arr[minQueue.getLast()] > arr[i]) {
        minQueue.removeLast();
      }
      // If lst element in the queue is less than current value, remove it
      while (!maxQueue.isEmpty() && arr[maxQueue.getLast()] < arr[i]) {
        maxQueue.removeLast();
      }
      minQueue.add(i);
      maxQueue.add(i);

      sum = sum + arr[maxQueue.getFirst()] - arr[minQueue.getFirst()];
    }
    return sum;
  }
}
