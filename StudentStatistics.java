import java.util.ArrayList;
import java.util.Scanner;

public class StudentStatistics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> marks = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter marks of student " + (i + 1) + ": ");
            marks.add(sc.nextInt());
        }

        int highest = marks.get(0);
        int lowest = marks.get(0);
        int sum = 0;

        for (int m : marks) {
            sum += m;

            if (m > highest)
                highest = m;

            if (m < lowest)
                lowest = m;
        }

        double average = (double) sum / n;

        System.out.println("\n--- Student Score Statistics ---");
        System.out.println("Average Score : " + average);
        System.out.println("Highest Score : " + highest);
        System.out.println("Lowest Score  : " + lowest);

        sc.close();
    }
}
