package studentmanagement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class StudentFile {
    public void saveStudentsFile(HashMap<Integer, Student> students) {
        try {
            FileWriter file = new FileWriter("students.txt");
            for (Student student : students.values()) {
                file.write(student.getId() + "," + student.getName() + ","+ student.getEmail() + ","+ student.getMarks() + ","+ student.getAttendance() + ","+ student.getStatus() + "\n");
            }
            file.close();
            System.out.println("Student details saved successfully");
        } catch (IOException e) {
            System.out.println("File error");
        }
    }

    public HashMap<Integer, Student> loadStudents() {
        HashMap<Integer, Student> students = new HashMap<>();

        try {
            File file = new File("students.txt");
            if (!file.exists()) {
                return students;
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String email = data[2];
                double marks = Double.parseDouble(data[3]);
                int attendance = Integer.parseInt(data[4]);
                StudentStatus status = StudentStatus.valueOf(data[5]);
                Student student = new Student(id, name, email, marks, attendance, status);
                students.put(id, student);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error while loading students");
        }
        return students;
    }
}