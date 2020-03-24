package LinkedList;

public class MergeLL {
  public static void main(String[] args) {
    SinglyLinkedList list1 = new SinglyLinkedList();
    int nodes1[] ={ 1, 2, 4, 5, 7, 8, 9 };// { 2, 3, 5, 6, 7 };
    SinglyLinkedList.createSLL(list1, nodes1);

    SinglyLinkedList list2 = new SinglyLinkedList();
    int nodes2[] = { 1, 2, 4, 5, 7, 8, 9 };
    SinglyLinkedList.createSLL(list2, nodes2);

    SLLNode mergeList = merge(list1.head, list2.head);
    SLLNode tmp = mergeList;
    while (tmp != null) {
      if (tmp.next == null) {
        System.out.println(tmp.data);
        break;
      }
      System.out.print(tmp.data + "->");
      tmp = tmp.next;
    }
  }

  private static SLLNode merge(SLLNode head1, SLLNode head2) {
    SLLNode prev = null;
    SLLNode head = null;
    SLLNode tmp1 = head1;
    SLLNode tmp2 = head2;

    while (tmp1 != null && tmp2 != null) {
      if (tmp2.data <= tmp1.data) {
        if (prev == null) {
          prev = tmp2;
          tmp2 = tmp2.next;
          head = prev;
        } else {

          prev.next = tmp2;
          prev = tmp2;
          tmp2 = tmp2.next;
        }
      } else {
        if (prev == null) {
          prev = tmp1;
          head = prev;
          tmp1 = tmp1.next;
        } else {
          prev.next = tmp1;
          prev = tmp1;
          tmp1 = tmp1.next;
        }
      }
    }
    if (tmp1 != null) {
      while (tmp1 != null) {
        prev.next = tmp1;
        prev = tmp1;
        tmp1 = tmp1.next;
      }
    }
    if (tmp2 != null) {
      while (tmp2 != null) {
        prev.next = tmp2;
        prev = tmp2;
        tmp2 = tmp2.next;
      }
    }

    return head;
  }
}
