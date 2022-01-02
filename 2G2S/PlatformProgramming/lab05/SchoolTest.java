package lab5;

import java.util.Scanner;

enum OperationKind {
    ADD, FIND, CLEAR, LIST, QUIT, INVALID
}

public class SchoolTest {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(final String[] args) {
        final School pnu = new School("PNU", 100);
        while (true) {
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
                    final Student newStudent = createStudent();
                    pnu.addStudent(newStudent);
                    break;
                }
                case FIND:
                    findStudent(pnu);
                    break;
                case CLEAR:
                    pnu.removeAllStudent();
                    break;
                case LIST:
                    System.out.println(pnu);
                    break;
                default:
                    break;
            }
        }
    }

    private static void findStudent(final School school) {
        final String studentName = scanner.next();
        final int schoolYear = scanner.nextInt();
        final Student foundStudent = school.findStudent(studentName, schoolYear);

        if (foundStudent != null)
            System.out.println(foundStudent);
        else
            System.out.println("Student Not Found with name " + studentName + " and year " + schoolYear);
    }

    private static Student createStudent() {
        final String studentName = scanner.next();
        final int schoolYear = scanner.nextInt();
        return new Student(studentName, schoolYear);
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
