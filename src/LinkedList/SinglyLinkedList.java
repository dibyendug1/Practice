package LinkedList;

public class SinglyLinkedList {
    SLLNode head;

    SLLNode insert(int data) {
        if (head == null) {
            head = new SLLNode(data);
            return head;
        }
        SLLNode node = getLastNode();
        SLLNode newNode = new SLLNode(data);
        node.next = newNode;
        return head;
    }

    private SLLNode getLastNode() {
        SLLNode node = head;
        while (node.next != null) {
            node = node.next;
        }
        return node;
    }

    void printSLL() {
        SLLNode node = head;
        while (node != null) {
            if (node.next == null) {
                System.out.println(node.data);
                node = node.next;
            } else {
                System.out.print(node.data + "->");
                node = node.next;
            }
        }
    }

    public static void createSLL(SinglyLinkedList list1, int[] nodes) {
        for (int data : nodes) {
            list1.head = list1.insert(data);
        }
    }

    public static void createSLL(PalindromeLL list1, int[] nodes) {
        for (int data : nodes) {
            list1.head = list1.insert(data);
        }
    }

    private SLLNode insertFirst(int data) {
        if (head == null) {
            head = new SLLNode(data);
            return head;
        }
        SLLNode node = new SLLNode(data);
        node.next = head;
        head = node;
        return head;
    }

    private void insertAfter(int data, int after) {
        SLLNode node = findNode(after);
        if (node == null) {
            System.out.println("Node not found");
            return;
        }
        SLLNode newNode = new SLLNode(data);
        newNode.next = node.next;
        node.next = newNode;
    }

    private SLLNode findNode(int after) {
        SLLNode node = head;
        while (node != null) {
            if (node.data == after) {
                break;
            }
            node = node.next;
        }
        return node;
    }

    private void deleteNode(int i) {
        if (head.data == i) {
            head = head.next;
            return;
        }
        SLLNode parentNode = findNodeParent(i);
        if (parentNode == null) {
            System.out.println("Node not found");
            return;
        }
        SLLNode node = parentNode.next;
        parentNode.next = parentNode.next.next;

    }

    private SLLNode findNodeParent(int i) {
        SLLNode node = head;
        while (node != null) {
            SLLNode next = node.next;
            if (next != null && next.data == i) {
                break;
            }
            node = node.next;
        }
        return node;
    }

    public static void main(String[] args) {
        SinglyLinkedList list1 = new SinglyLinkedList();
/*        int data = 50;
        list1.head = list1.insert(data);
        list1.head = list1.insertFirst(30);
        list1.head = list1.insertFirst(70);
        list1.insertAfter(40, 30);
        System.out.println("Print Singly Linkedlist1");
        list1.printSLL();

        int del = 40;
        System.out.println("Deleating node...");
        list1.deleteNode(33);
        list1.printSLL();*/
        int[] nodes = {30, 20, 14, 67, 54, 34, 27, 87, 56, 23, 11, 31, 25, 46,
                78, 8};
        createSLL(list1, nodes);
        SinglyLinkedList list2 = new SinglyLinkedList();
        int[] nodes2 = {12, 43, 65, 13, 78, 54, 42, 16, 57, 76, 43, 14, 31, 67};
        createSLL(list2, nodes2);

        System.out.println("Print Singly Linkedlist1");
        list1.printSLL();
        System.out.println("Length:" + list1.length());
        int n = 10;
        System.out.printf("get %dth node: %s\n", n, list1.getNode(n).toString
                ());
        System.out.println("Print Singly Linkedlist2");
        list2.printSLL();

        list1.head = reverseLinkedlist(list1);

        System.out.println("Reversed Singly Linkedlist1");
        list1.printSLL();

        recursiveReverseLinkedlist(list1);

        System.out.println("Recursive Reversed Singly Linkedlist1");
        list1.printSLL();

        SinglyLinkedList list3 = new SinglyLinkedList();
        long number = 654453257878578425L;
        representNumber(list3, number);
        System.out.println("Number in Singly Linkedlist");
        list3.printSLL();
    }

    private static void recursiveReverseLinkedlist(SinglyLinkedList list1) {
        SLLNode node = list1.head;
        reverse(node, list1);
    }

    private static SLLNode reverse(SLLNode node, SinglyLinkedList list) {
        if(node == null) {
            return null;
        }

        SLLNode tmp = reverse(node.next, list);
        if(node.next == null) {
            list.head = node;
        }
        if(tmp!=null) {
            tmp.next = node;
            node.next = null;
        }
        return node;
    }

    private SLLNode getNode(int n) {
        int count = 1;
        SLLNode tmp = head;
        while (count < n) {
            tmp = tmp.next;
            count += 1;
        }
        return tmp;
    }

    private int length() {
        int len = 0;
        SLLNode tmp = head;
        while (tmp != null) {
            tmp = tmp.next;
            len += 1;
        }
        return len;
    }

    private static void representNumber(SinglyLinkedList list, long number) {
        long num = number;
        while (num != 0) {
            int d = (int) (num % 10L);
            list.insertFirst(d);
            num = num / 10L;
        }
    }

    private static SLLNode reverseLinkedlist(SinglyLinkedList list1) {
        SLLNode t1, t2, t3;
        t3 = list1.head;
        t1 = t2 = null;
        while (t3 != null) {
            t2 = t3;
            t3 = t3.next;
            t2.next = t1;
            t1 = t2;
        }
        return t1;
    }
}
