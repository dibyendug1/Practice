package Misc;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListQuery {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = scan.nextLine();
            ArrayList<Integer> subList = new ArrayList<>();
            for (String item : line.split(" ")) {
                subList.add(Integer.parseInt(item));
            }
            list.add(subList);
        }
        int q = scan.nextInt();
        for (int i = 0; i < q; i++) {
            int listNum = scan.nextInt();
            int pos = scan.nextInt();
            try {
                System.out.println(list.get(listNum - 1).get(pos));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("ERROR!");
            }
        }
    }
}
