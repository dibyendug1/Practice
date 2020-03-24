package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

public class GeneratePowerSet {
  public static void main(String[] args) {
    ArrayList<Integer> set = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    ArrayList<ArrayList<Integer>> powerSet = getPowerSet(set, 0);
    System.out.println(powerSet.toString());

    ArrayList<ArrayList<Integer>> powerSetItr = getPowerSetItr(set);
    System.out.println(powerSetItr.toString());
  }

  private static ArrayList<ArrayList<Integer>> getPowerSetItr(ArrayList<Integer> set) {
    ArrayList<ArrayList<Integer>> allSubSets = new ArrayList<>();
    allSubSets.add(new ArrayList<>()); //Add empty set
    for (int element : set) { // Take each element and generate power set elements
      ArrayList<ArrayList<Integer>> moreSubSets = new ArrayList<>();
      for (ArrayList<Integer> subset : allSubSets) {
        ArrayList<Integer> newSubSets = new ArrayList<>(); // New element on the powerset
        newSubSets.addAll(subset);
        newSubSets.add(element);
        moreSubSets.add(newSubSets);
      }
      allSubSets.addAll(moreSubSets);
    }
    return allSubSets;
  }

  private static ArrayList<ArrayList<Integer>> getPowerSet(ArrayList<Integer> set, int index) {
    ArrayList<ArrayList<Integer>> allSubSets = new ArrayList<>();
    if (set.size() == index) {
      allSubSets = new ArrayList<>();
      allSubSets.add(new ArrayList<>());
    } else {
      allSubSets = getPowerSet(set, index + 1);
      int item = set.get(index);
      ArrayList<ArrayList<Integer>> moreSubSets = new ArrayList<>();
      for (ArrayList<Integer> subset : allSubSets) {
        ArrayList<Integer> newSubSets = new ArrayList<>();
        newSubSets.addAll(subset);
        newSubSets.add(item);
        moreSubSets.add(newSubSets);
      }
      allSubSets.addAll(moreSubSets);
    }
    return allSubSets;
  }

}
