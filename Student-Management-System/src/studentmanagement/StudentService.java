package studentmanagement;

import java.util.HashMap;

public class StudentService {
    HashMap<Integer, Student> students = new HashMap<>();

    public HashMap<Integer, Student> getStudents() {
        return students;
    }
    public void setStudents(HashMap<Integer, Student> students) {
        this.students = students;
    }

    public void addStudent(Student student)throws DuplicateStudentIdException, InvalidMarksException, InvalidAttendanceException {
        if (students.containsKey(student.getId())) {
            throw new DuplicateStudentIdException("Student ID already exists");
        }
        if (student.getMarks() < 0 || student.getMarks() > 100) {
            throw new InvalidMarksException("Marks must be between 0 and 100");
        }
        if (student.getAttendance() < 0 || student.getAttendance() > 100) {
            throw new InvalidAttendanceException("Attendance must be between 0 and 100");
        }
        students.put(student.getId(), student);
        System.out.println("Student added successfully");
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found");
        } else {
            for (Student student : students.values()) {
                System.out.println("ID : " + student.getId());
                System.out.println("Name : " + student.getName());
                System.out.println("Email : " + student.getEmail());
                System.out.println("Marks : " + student.getMarks());
                System.out.println("Attendance : " + student.getAttendance());
                System.out.println("Status : " + student.getStatus());
                System.out.println("----------------------");
            }
        }
    }

    public void searchById(int id) {
        if (students.containsKey(id)) {
            Student student = students.get(id);
            System.out.println("ID : " + student.getId());
            System.out.println("Name : " + student.getName());
            System.out.println("Email : " + student.getEmail());
            System.out.println("Marks : " + student.getMarks());
            System.out.println("Attendance : " + student.getAttendance());
            System.out.println("Status : " + student.getStatus());
            System.out.println("----------------------");
        } else {
            System.out.println("No student found");
        }
    }

    public void searchByName(String name) {
        for (Student student : students.values()) {
            if (student.getName().equalsIgnoreCase(name)) {
                System.out.println("ID : " + student.getId());
                System.out.println("Name : " + student.getName());
                System.out.println("Email : " + student.getEmail());
                System.out.println("Marks : " + student.getMarks());
                System.out.println("Attendance : " + student.getAttendance());
                System.out.println("Status : " + student.getStatus());
                System.out.println("----------------------");
                return;
            }
        }
        System.out.println("No student found");
    }

    public void updateMarks(int id, double marks) throws InvalidMarksException {
        if (marks < 0 || marks > 100) {
            throw new InvalidMarksException("Marks must be between 0 and 100");
        }
        if (students.containsKey(id)) {
            students.get(id).setMarks(marks);
            System.out.println("Marks updated successfully");
        } else {
            System.out.println("No student found");
        }
    }

    public void updateAttendance(int id, int attendance) throws InvalidAttendanceException {
        if (attendance < 0 || attendance > 100) {
            throw new InvalidAttendanceException("Attendance must be between 0 and 100");
        }
        if (students.containsKey(id)) {
            students.get(id).setAttendance(attendance);
            System.out.println("Attendance updated successfully");
        } else {
            System.out.println("No student found");
        }
    }

    public void calculateGrade(int id) {
        if (students.containsKey(id)) {
            double marks = students.get(id).getMarks();
            if (marks >= 90) {
                System.out.println("O grade");
            } else if (marks >= 75) {
                System.out.println("A grade");
            } else if (marks >= 50) {
                System.out.println("B grade");
            } else if (marks >= 35) {
                System.out.println("Pass");
            } else {
                System.out.println("Fail");
            }
        } else {
            System.out.println("No student found");
        }
    }
    public void removeStudent(int id) {
        if (students.containsKey(id)) {
            students.remove(id);
            System.out.println("Student removed successfully");
        } else {
            System.out.println("No student found");
        }
    }

    public void displayTopper() {
        if (students.isEmpty()) {
            System.out.println("No students found");
        } else {
            Student topper = null;

            for (Student student : students.values()) {
                if (topper == null || student.getMarks() > topper.getMarks()) {
                    topper = student;
                }
            }
            System.out.println("Topper Details");
            System.out.println("ID : " + topper.getId());
            System.out.println("Name : " + topper.getName());
            System.out.println("Marks : " + topper.getMarks());
        }
    }
}