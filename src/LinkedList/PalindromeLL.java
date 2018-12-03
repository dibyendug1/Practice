package LinkedList;

public class PalindromeLL extends SinglyLinkedList{
  static SLLNode head;
  public static void main(String[] args) {
    PalindromeLL list1 = new PalindromeLL();
    int[] nodes = {0, 1, 1, 0};
    SinglyLinkedList.createSLL(list1, nodes);
    list1.printSLL();
    System.out.println("Is Palindrome : " + checkPalindrome(list1.head));
  }

  private static boolean checkPalindrome(SLLNode node) {
    if(node == null) {
      return true;
    }
    boolean ret = checkPalindrome(node.next);
    boolean res = (head.data == node.data);
    head = head.next;
    return res && ret;
  }

}
