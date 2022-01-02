package edu.pnu.edit;

import java.util.Scanner;
import edu.pnu.shape.*;

enum OperationKind {
    ADDR, ADDC, LIST, CLEAR, QUIT, INVALID
};

public class EditorTest {
    private static Editor editor = new Editor();

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        while (true) {
            final OperationKind op = getOperation(scanner);

            if (op == OperationKind.QUIT) {
                System.out.println("Bye");
                break;
            }

            if (op == OperationKind.INVALID) {
                System.out.println("Invalid Operation!");
                continue;
            }

            switch (op) {
                case ADDR: {
                    final Rectangle newRectangle = createRectangle(scanner);
                    System.out.println(newRectangle);

                    editor.add(newRectangle);
                    break;
                }

                case ADDC: {
                    final Circle newCircle = createCircle(scanner);
                    System.out.println(newCircle);

                    editor.add(newCircle);
                    break;
                }

                case CLEAR:
                    editor.clear();
                    break;

                case LIST:
                    editor.list();
                    break;

                default:
                    break;
            }
        }

        scanner.close();
    }

    private static Circle createCircle(final Scanner scanner) {
        final int x = scanner.nextInt();
        final int y = scanner.nextInt();
        final int radius = scanner.nextInt();

        final Circle newCircle = new Circle(new Point(x, y), radius);
        return newCircle;
    }

    private static Rectangle createRectangle(final Scanner scanner) {
        final int width = scanner.nextInt();
        final int height = scanner.nextInt();

        final Rectangle newRectangle = new Rectangle(width, height);
        return newRectangle;
    }

    private static OperationKind getOperation(final Scanner scanner) {
        System.out.print("Enter Operation String! ");
        final String operation = scanner.next();

        OperationKind kind;

        try {
            kind = OperationKind.valueOf(operation.toUpperCase());
        } catch (IllegalArgumentException e) {
            kind = OperationKind.INVALID;
        }

        return kind;
    }
}