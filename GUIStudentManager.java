import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
class Student {
    String name;
    int marks;
    char grade;
    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
        this.grade = (marks >= 90) ? 'A' :
                     (marks >= 75) ? 'B' :
                     (marks >= 60) ? 'C' :
                     (marks >= 40) ? 'D' : 'F';
    }
}
public class GUIStudentManager extends JFrame {
    ArrayList<Student> students = new ArrayList<>();
    JTextArea display;
    public GUIStudentManager() {
        setTitle("Student Grade Manager");
        setSize(400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JTextField nameField = new JTextField(10);
        JTextField marksField = new JTextField(5);
        JButton addBtn = new JButton("Add Student");
        JButton reportBtn = new JButton("Show Report");
        display = new JTextArea();
        display.setEditable(false);
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Marks:"));
        inputPanel.add(marksField);
        inputPanel.add(addBtn);
        inputPanel.add(reportBtn);
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(display), BorderLayout.CENTER);
        addBtn.addActionListener(e -> {
            String name = nameField.getText();
            int marks = Integer.parseInt(marksField.getText());
            students.add(new Student(name, marks));
            nameField.setText("");
            marksField.setText("");
        });
        reportBtn.addActionListener(e -> {
            display.setText("Name\tMarks\tGrade\n");
            for (Student s : students) {
                display.append(s.name + "\t" + s.marks + "\t" + s.grade + "\n");
            }
        });
    }
    public static void main(String[] args) {
        new GUIStudentManager().setVisible(true);
    }
}

