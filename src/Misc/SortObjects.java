package Misc;

import java.util.*;

class Student {
  private int id;
  private String fname;
  private double cgpa;

  public Student(int id, String fname, double cgpa) {
    super();
    this.id = id;
    this.fname = fname;
    this.cgpa = cgpa;
  }

  public int getId() {
    return id;
  }

  public String getFname() {
    return fname;
  }

  public double getCgpa() {
    return cgpa;
  }
}

//Complete the code
public class SortObjects {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int testCases = Integer.parseInt(in.nextLine());

    List<Student> studentList = new ArrayList<Student>();
    while (testCases > 0) {
      int id = in.nextInt();
      String fname = in.next();
      double cgpa = in.nextDouble();

      Student st = new Student(id, fname, cgpa);
      studentList.add(st);

      testCases--;
    }

        /*studentList.sort((o1, o2) -> {
            return o2.getCgpa() - o1.getCgpa() != 0 ? o2.getCgpa() - o1.getCgpa() > 0 ? 0 : -1 : o2.getId() - o1.getId() > 0 ? -1 : 0;
        });*/
    Collections.sort(studentList,
        Comparator.comparing(Student::getCgpa).reversed().thenComparing(Comparator.comparing(Student::getFname))
            .thenComparing(Comparator.comparing(Student::getId)));
    for (Student st : studentList) {
      System.out.println(st.getFname());
    }
  }
}
/*      5
        33 Rumpa 3.68
        85 Ashis 3.85
        56 Aamiha 3.75
        19 Samara 3.75
        22 Fahim 3.75*/