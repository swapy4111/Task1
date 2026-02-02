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
    void display() {
        System.out.println(name + "\t" + marks + "\t" + grade);
    }
}
public class StudentGradeManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        while (true) {
            System.out.println("\n--- Student Grade Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter marks (0-100): ");
                    int marks = sc.nextInt();
                    students.add(new Student(name, marks));
                    System.out.println("Student added successfully!");
                    break;
                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No student records found.");
                    } else {
                        System.out.println("\nName\tMarks\tGrade");
                        System.out.println("----------------------");
                        for (Student s : students) {
                            s.display();
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
