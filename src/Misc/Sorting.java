package Misc;

import java.util.Arrays;

public class Sorting {
  public static void main(String[] args) {
    int[] array = { 12, 32, 1, 34, 76, 54, 34, 3, 6, 87, 343, 32, 45 };
    System.out.println("Before sorting:");
    System.out.println(Arrays.toString(array));
    sortAsc(array);
    System.out.println("After sorting:");
    System.out.println(Arrays.toString(array));
  }

  private static void sortAsc(int[] array) {
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array.length - 1 - i; j++) {
        if (array[i] < array[j]) {
          swap(array, i, j);
        }
      }
    }
  }

  private static void swap(int[] array, int i, int j) {
    int t = array[i];
    array[i] = array[j];
    array[j] = t;
  }
}
