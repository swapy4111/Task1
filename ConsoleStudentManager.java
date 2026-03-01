import java.util.ArrayList;
import java.util.Scanner;
class Student {
    String name;
    int marks;
    char grade;
    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
        this.grade = calculateGrade(marks);
    }
    private char calculateGrade(int marks) {
        if (marks >= 90) return 'A';
        else if (marks >= 75) return 'B';
        else if (marks >= 60) return 'C';
        else if (marks >= 40) return 'D';
        else return 'F';
    }
}
public class ConsoleStudentManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("\nStudent " + (i + 1));
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Marks: ");
            int marks = sc.nextInt();
            sc.nextLine();
            students.add(new Student(name, marks));
        }
        int total = 0, highest = students.get(0).marks, lowest = students.get(0).marks;
        for (Student s : students) {
            total += s.marks;
            if (s.marks > highest) highest = s.marks;
            if (s.marks < lowest) lowest = s.marks;
        }
        double avg = (double) total / students.size();
        System.out.println("\n--- STUDENT SUMMARY REPORT ---");
        System.out.println("Name\tMarks\tGrade");
        for (Student s : students) {
            System.out.println(s.name + "\t" + s.marks + "\t" + s.grade);
        }
        System.out.println("\nAverage: " + avg);
        System.out.println("Highest: " + highest);
        System.out.println("Lowest : " + lowest);
        sc.close();
    }
}
