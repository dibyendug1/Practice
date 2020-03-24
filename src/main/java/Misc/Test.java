package Misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test {
  public static void main(String[] args) throws IOException {
    String a = "A";
    String b = "B";
    System.out.println(a.equals(b));
    System.out.println(a.compareTo(b));

    File f = new File("/Users/dibyendu.karmakar/.bash_profile");

    BufferedReader br = new BufferedReader(new FileReader(f));

    String st;
    while ((st = br.readLine()) != null)
      System.out.println(st);
  }
}
