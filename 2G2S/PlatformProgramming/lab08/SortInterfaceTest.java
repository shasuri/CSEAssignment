package lab8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SortInterfaceTest {

    enum OperationKind {
        ADDL, ADDC, SORTA, SORTD, CLEAR, LIST, QUIT, INVALID
    }

    enum SortKind {
        ASCENDING, DESCENDING
    }

    private static Scanner scanner = new Scanner(System.in);
    private static List<MyComparable> comparableList = new ArrayList<>();

    public static void main(String[] args) {
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
                case ADDL: {
                    final Line newLine = createLine(scanner);
                    comparableList.add(newLine);
                    System.out.println(newLine);
                    break;
                }

                case ADDC: {
                    final Circle newCircle = createCircle(scanner);
                    comparableList.add(newCircle);
                    System.out.println(newCircle);
                    break;
                }

                case SORTA:
                    sortList(comparableList, SortKind.ASCENDING);
                    break;

                case SORTD:
                    sortList(comparableList, SortKind.DESCENDING);
                    break;

                case CLEAR:
                    comparableList.clear();
                    break;

                case LIST:
                    System.out.println(comparableList);
                    break;

                default:
                    break;
            }
        }
    }

    private static OperationKind getOperation(Scanner scanner) {
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

    private static Line createLine(Scanner scanner) {
        final int firstX = scanner.nextInt();
        final int firstY = scanner.nextInt();
        final int secondX = scanner.nextInt();
        final int secondY = scanner.nextInt();

        final Point firstPoint = new Point(firstX, firstY);
        final Point secondPoint = new Point(secondX, secondY);

        final Line newLine = new Line(firstPoint, secondPoint);

        return newLine;
    }

    private static Circle createCircle(Scanner scanner) {
        final int centerX = scanner.nextInt();
        final int centerY = scanner.nextInt();
        final int radius = scanner.nextInt();

        final Point centerPoint = new Point(centerX, centerY);
        final Circle newCircle = new Circle(centerPoint, radius);

        return newCircle;
    }

    private static void sortList(final List<MyComparable> comparableList, final SortKind sortKind) {
        int compareResult = 0;
        int sortSignal = 0;

        if (sortKind == SortKind.ASCENDING)
            sortSignal = 1;

        if (sortKind == SortKind.DESCENDING)
            sortSignal = -1;

        for (int i = 0; i < comparableList.size(); i++) {
            for (int j = 0; j < comparableList.size() - i - 1; j++) {
                compareResult = comparableList.get(j).compareTo(comparableList.get(j + 1));

                if (compareResult == sortSignal)
                    swapForward(comparableList, j);

            }
        }
    }

    private static void swapForward(List<MyComparable> comparableList, final int swapIndex) {
        final MyComparable temp = comparableList.get(swapIndex);
        comparableList.set(swapIndex, comparableList.get(swapIndex + 1));
        comparableList.set(swapIndex + 1, temp);
    }
}