package studentmanagement;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();
        StudentFile fileService = new StudentFile();

        service.setStudents(fileService.loadStudents());

        while (true) {
            System.out.println();
            System.out.println("===== Student Management System =====");
            System.out.println("1. Add student");
            System.out.println("2. Display all students");
            System.out.println("3. Search by ID");
            System.out.println("4. Search by name");
            System.out.println("5. Update marks");
            System.out.println("6. Update attendance");
            System.out.println("7. Calculate grade");
            System.out.println("8. Remove student");
            System.out.println("9. Display topper");
            System.out.println("10. Save to file");
            System.out.println("0. Save and exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = sc.nextInt();

                if (choice == 1) {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter name: ");
                    String name = sc.next();
                    System.out.print("Enter email: ");
                    String email = sc.next();
                    System.out.print("Enter marks: ");
                    double marks = sc.nextDouble();
                    System.out.print("Enter attendance: ");
                    int attendance = sc.nextInt();
                    System.out.print("Enter status (ACTIVE / INACTIVE / COMPLETED): ");
                    StudentStatus status = StudentStatus.valueOf(sc.next().toUpperCase());

                    Student student = new Student(id, name, email, marks, attendance, status);
                    service.addStudent(student);

                } else if (choice == 2) {
                    service.displayAllStudents();

                } else if (choice == 3) {
                    System.out.print("Enter ID: ");
                    service.searchById(sc.nextInt());

                } else if (choice == 4) {
                    System.out.print("Enter name: ");
                    service.searchByName(sc.next());

                } else if (choice == 5) {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter new marks: ");
                    double marks = sc.nextDouble();
                    service.updateMarks(id, marks);

                } else if (choice == 6) {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter new attendance: ");
                    int attendance = sc.nextInt();
                    service.updateAttendance(id, attendance);

                } else if (choice == 7) {
                    System.out.print("Enter ID: ");
                    service.calculateGrade(sc.nextInt());

                } else if (choice == 8) {
                    System.out.print("Enter ID: ");
                    service.removeStudent(sc.nextInt());

                } else if (choice == 9) {
                    service.displayTopper();

                } else if (choice == 10) {
                    fileService.saveStudentsFile(service.getStudents());

                } else if (choice == 0) {
                    fileService.saveStudentsFile(service.getStudents());
                    System.out.println("Thank you!");
                    break; // stops the while loop

                } else {
                    System.out.println("Invalid choice");
                }

            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Please enter correct value.");
                sc.nextLine(); 
            } catch (InvalidMarksException e) {
				e.printStackTrace();
			} catch (InvalidAttendanceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateStudentIdException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        }

        sc.close();
    }
}