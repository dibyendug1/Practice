package Misc;

public class SubarraySum {
  public static void main(String[] args) {
    int arr[] = { 15, 2, 4, 8, 10, 5, 10, 24 };
    int n = arr.length;
    int sum = 23;
    subArraySum(arr, n, sum);
  }

  private static void subArraySum(int[] arr, int n, int sum) {
    int cur_sum;
    int start = 0;
    cur_sum = arr[start];
    for (int i = 1; i <= n; i++) {
      //If current sum exceeds sum, remove starting elements
      while (cur_sum > sum && start < i - 1) {
        cur_sum = cur_sum - arr[start];
        start++;
      }

      // If cur_sum is equal to sum, print and return
      if (cur_sum == sum) {
        int p = i - 1;
        System.out.printf("subarray starts at %d ends at %d", start, p);
        return;

      }

      // Calculate cut_sum while i<n
      if (i < n) {
        cur_sum += arr[i];
      }
    }

    System.out.println("No subarray found");
  }
}
