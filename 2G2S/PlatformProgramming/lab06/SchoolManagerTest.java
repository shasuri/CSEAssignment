package lab6;

import java.util.Scanner;
import java.util.List;

public class SchoolManagerTest {
    private static Scanner scanner = new Scanner(System.in);
    private static SchoolManager schoolManager = new SchoolManager();

    public static void main(String[] args) {
        while (true) {
            System.out.print("Enter Operation String! ");
            final OperationKind op = getOperation();

            if (op == OperationKind.QUIT) {
                System.out.println("Bye");
                break;
            }

            if (op == OperationKind.INVALID) {
                System.out.println("Invalid Operation!");
                continue;
            }

            switch (op) {
                case ADD: {
                    Student newStudent = createStudent();
                    System.out.println(newStudent);
                    break;
                }
                case FIND:
                    findStudent();
                    break;
                case CLEAR:
                    schoolManager.removeAllSchools();
                    break;
                case LIST:
                    System.out.println(schoolManager);
                    break;
            }
        }
    }

    enum OperationKind {
        ADD, FIND, CLEAR, LIST, QUIT, INVALID
    }

    private static Student createStudent() {
        final String schoolName = scanner.next();
        final String studentName = scanner.next();
        final int schoolYear = scanner.nextInt();

        School theSchool = schoolManager.findSchool(schoolName);

        if (theSchool == null)
            theSchool = schoolManager.createStudent(schoolName);

        final Student newStudent = new Student(theSchool, studentName, schoolYear);

        theSchool.addStudent(newStudent);
        return newStudent;

    }

    private static void findStudent() {
        final String studentName = scanner.next();
        final int schoolYear = scanner.nextInt();
        final List<Student> foundStudents = schoolManager.findStudent(studentName, schoolYear);

        if (foundStudents.size() > 0) {
            System.out.println(foundStudents.size() + " found");
            for (Student s : foundStudents)
                System.out.println(s);
        } else
            System.out.println("No Student Found with name " + studentName + " and year " + schoolYear);
    }

    private static OperationKind getOperation() {
        final String command = scanner.next();
        OperationKind operationInput = OperationKind.INVALID;

        try {
            operationInput = OperationKind.valueOf(command.toUpperCase());
        } catch (final Exception e) {
        }

        return operationInput;
    }
}