package Tree;

public class AVLNode {
    int data;
    AVLNode left;
    AVLNode right;
    int height;

    public AVLNode(int data) {
        this.data = data;
        height = 1;
        this.left = this.right = null;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
