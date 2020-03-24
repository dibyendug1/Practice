package Misc;

import java.util.ArrayList;
import java.util.List;

public class ReverseDepthSum {
  static int revsum = 0;
  static int tdepth = Integer.MIN_VALUE;

  public static void main(String[] args) {
    List<NestedInteger> list1 = new ArrayList<>();
    NestedInteger i1 = new NestedInteger();
    i1.setInteger(1);
    list1.add(i1);
    list1.add(i1);
    NestedInteger nI = new NestedInteger(list1);
    NestedInteger i2 = new NestedInteger();
    i2.setInteger(2);
    List<NestedInteger> list2 = new ArrayList<>();
    list2.add(nI);
    list2.add(i2);
    list2.add(nI);

    NestedInteger root = new NestedInteger(list2);
    System.out.println(root.printNi(root, new StringBuilder()));

    System.out.println("Depth sum:");
    int sum = reverseDepthSum(list2);
    System.out.println(sum);
    int rsum = revsum * (tdepth + 1) - sum;
    System.out.println(rsum);
  }

  private static int reverseDepthSum(List<NestedInteger> nestedList) {
    return helper(nestedList, 1);
  }

  private static int helper(List<NestedInteger> nestedList, int depth) {
    if (nestedList == null || nestedList.size() == 0)
      return 0;

    int sum = 0;

    for (NestedInteger ni : nestedList) {
      if (ni.isInteger()) {
        sum += ni.getInteger() * depth;
        revsum += ni.getInteger();
        if (depth > tdepth) {
          tdepth = depth;
        }
      } else {
        sum += helper(ni.getList(), depth + 1);
      }
    }

    return sum;
  }
}
