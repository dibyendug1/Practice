package DynamicProgramming;

public class TOH {
  public static void main(String[] args) {
    int num = 3;
    tohMoves(num, "A", "B", "C");
  }

  private static void tohMoves(int num, String a, String b, String c) {
    if (num == 1) {
      System.out.println("move " + num + " from " + a + "->" + b);
      return;
    }
    tohMoves(num - 1, a, c, b);
    System.out.println("move " + num + " from " + a + "->" + b);
    tohMoves(num - 1, c, b, a);
  }
}
