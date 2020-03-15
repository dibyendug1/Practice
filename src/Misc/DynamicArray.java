package Misc;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class DynamicArray {

  /*
   * Complete the 'dynamicArray' function below.
   *
   * The function is expected to return an INTEGER_ARRAY.
   * The function accepts following parameters:
   *  1. INTEGER n
   *  2. 2D_INTEGER_ARRAY queries
   */

  public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
    // Write your code here
    List<ArrayList<Integer>> seqList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      seqList.add(new ArrayList<Integer>());
    }

    List<Integer> lastAnswers = new ArrayList<>();
    int lastAnswer = 0;
    for (List<Integer> q : queries) {
      int qtype = q.get(0);
      int x = q.get(1);
      int y = q.get(2);
      int pos = ((x ^ lastAnswer) % n);
      switch (qtype) {
      case 1:
        if (seqList.size() < pos + 1) {
          seqList.add(pos, new ArrayList<>());
        }
        seqList.get(pos).add(y);
        break;
      case 2:
        ArrayList<Integer> seq = seqList.get(pos);
        lastAnswer = seq.get(y % seq.size());
        lastAnswers.add(lastAnswer);
      }
    }

    return lastAnswers;

  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int n = Integer.parseInt(firstMultipleInput[0]);

    int q = Integer.parseInt(firstMultipleInput[1]);

    List<List<Integer>> queries = new ArrayList<>();

    IntStream.range(0, q).forEach(i -> {
      try {
        queries.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
            .collect(toList()));
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    List<Integer> result = DynamicArray.dynamicArray(n, queries);
    System.out.println(result.stream().map(Object::toString).collect(joining("\n")) + "\n");

    //bufferedWriter.write(result.stream().map(Object::toString).collect(joining("\n")) + "\n");

    bufferedReader.close();
    //bufferedWriter.close();
  }
}
