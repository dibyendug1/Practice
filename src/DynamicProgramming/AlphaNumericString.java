package DynamicProgramming;

import java.io.*;

/**
 * Find Number of Strings can be generated using the input digits.
 * Alphabets are represented by digits 1 to 26
 */
public class AlphaNumericString {
  /*
   * Complete the 'calculatePossibleCombinations' function below.
   *
   * The function is expected to return a LONG_INTEGER.
   * The function accepts STRING inputStr as parameter.
   */

  public static long calculatePossibleCombinations(String inputStr) {
    long count[] = new long[inputStr.length() + 1];
    count[0] = 1;
    count[1] = 1;
    if (inputStr.charAt(0) == '0') {
      return 0;
    }

    for (int i = 2; i <= inputStr.length(); i++) {
      count[i] = 0;
      if (inputStr.charAt(i - 1) > '0') {
        count[i] = count[i - 1];
      }
      if (inputStr.charAt(i - 2) == '1' || (inputStr.charAt(i - 2) == '2' && inputStr.charAt(i - 1) < '7')) {
        count[i] = count[i] + count[i - 2];
      }
    }

    return count[inputStr.length()];

  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String inputStr = bufferedReader.readLine();

    long result = calculatePossibleCombinations(inputStr);

    System.out.println(result);
    //bufferedWriter.write(String.valueOf(result));
    //bufferedWriter.newLine();

    bufferedReader.close();
    //bufferedWriter.close();
  }
}

