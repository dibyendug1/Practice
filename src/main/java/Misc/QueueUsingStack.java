package Misc;

import java.util.Scanner;
import java.util.Stack;

public class QueueUsingStack {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    for (int i = 0; i < n; i++) {
      int query = scan.nextInt();
      switch (query) {
      case 1:
        int data = scan.nextInt();
        enqueue(data, stack1, stack2);
        break;
      case 2:
        dequeue(stack1, stack2);
        break;
      case 3:
        printTop(stack1, stack2);
      }
    }
  }

  private static void printTop(Stack<Integer> stack1, Stack<Integer> stack2) {
    if (stack2.empty()) {
      while (!stack1.empty()) {
        stack2.push(stack1.pop());
      }
    }
    System.out.println(stack2.peek());
  }

  private static void dequeue(Stack<Integer> stack1, Stack<Integer> stack2) {
    if (stack2.empty()) {
      while (!stack1.empty()) {
        stack2.push(stack1.pop());
      }
    }
    stack2.pop();
  }

  private static void enqueue(int data, Stack<Integer> stack1, Stack<Integer> stack2) {
    /*while (!stack2.empty()) {
      stack1.push(stack2.pop());
    }*/
    stack1.push(data);
  }
}
