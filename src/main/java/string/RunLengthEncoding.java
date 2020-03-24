package string;

import java.io.*;

/**
 * Run length encoding of a String
 * GGGGGrrrrrrrrrrrrrrrrrhhhht
 * 5G17r4h1t
 */
public class RunLengthEncoding {
  /*
   * Complete the 'collapseString' function below.
   *
   * The function is expected to return a STRING.
   * The function accepts STRING inputString as parameter.
   */

  public static String collapseString(String inputString) {
    if (inputString == null || inputString.length() == 0) {
      return inputString;
    }

    String res = "";
    int count = 1;
    char last = inputString.charAt(0);
    for (int i = 1; i < inputString.length(); i++) {
      if (last == inputString.charAt(i)) {
        count++;
        continue;
      }
      res = res + count + last;
      last = inputString.charAt(i);
      count = 1;
    }
    res = res + count + last;

    return res;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    String inputStr = bufferedReader.readLine();
    String result = collapseString(inputStr);

    System.out.println(result);
    bufferedReader.close();
  }
}
