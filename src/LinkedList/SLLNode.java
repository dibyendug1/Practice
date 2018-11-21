package LinkedList;

public class SLLNode {
    int data;
    SLLNode next;

    public SLLNode(int data) {
        this.data = data;
        this.next = null;
    }

    @Override
    public String toString() {
        return Integer.toString(data);
    }
}
