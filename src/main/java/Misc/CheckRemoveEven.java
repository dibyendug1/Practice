package Misc;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckRemoveEven {
    public static int[] removeEven(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : arr) {
            if(i%2!=0){
                list.add(i);
            }
        }
        int[] ret = new int[5];

        //int[] ret = new int[list.size()];
        int pos = 0;
        for (int i : list) {
            ret[pos++] = i;
        }
        // Write - Your - Code- Here
        return ret; // change this and return the correct result array
    }

    // without list
    public static int[] removeEven2(int[] arr) {
        int odd = 0;
        for (int i : arr) {
            if(i%2!=0){
                odd++;
            }
        }
        int[] ret = new int[odd];

        int pos = 0;
        for (int i : arr) {
            if(i%2!=0){
                ret[pos++] = i;
            }
        }
        // Write - Your - Code- Here
        return ret; // change this and return the correct result array
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 10, 6, 3};
        int[] res = removeEven2(arr);
        System.out.println(Arrays.toString(res));
    }
}
