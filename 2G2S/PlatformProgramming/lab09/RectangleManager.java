package lab9;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RectangleManager {
    private List<Rectangle> rectangles = new ArrayList<>();
    private Scanner scannerObject;

    public static void main(String[] args) {
        Scanner scannerObject = new Scanner(System.in);
        RectangleManager manager = new RectangleManager(scannerObject);

        while (true) {
            System.out.println("Enter a command: [create width height, zoom id ratio or quit]");

            final String command = scannerObject.next();

            if (command.equalsIgnoreCase("create")) {
                try {
                    manager.create();
                } catch (InvalidRectangleException e) {
                    System.out.println("사각형의 넓이와 높이는 양수이어야 합니다." + e.getWidth() + ' ' + e.getHeight());
                } catch (InputMismatchException e) {
                    System.out.println("입력된 인자의 형식이 맞지 않습니다.");
                }
            }

            else if (command.equalsIgnoreCase("zoom")) {
                manager.zoom();
            }

            else if (command.equalsIgnoreCase("showAll")) {
                manager.showAll();
            }

            else if (command.equalsIgnoreCase("quit")) {
                System.out.println("Bye");
                break;
            }
        }
        scannerObject.close();
    }

    private RectangleManager(final Scanner scannerObject) {
        this.scannerObject = scannerObject;
    }

    private void create() throws InvalidRectangleException, InputMismatchException {
        final int recLocate = rectangles.size();
        final int recWidth = scannerObject.nextInt();
        final int recHeight = scannerObject.nextInt();

        if (recWidth <= 0 || recHeight <= 0) {
            InvalidRectangleException e = new InvalidRectangleException(recWidth, recHeight);
            throw e;
        }

        final Rectangle createdRectangle = new Rectangle(recWidth, recHeight);

        rectangles.add(createdRectangle);

        System.out.println(createdRectangle + " is added at " + recLocate);
    }

    private void zoom() throws IndexOutOfBoundsException {
        final int zoomLocate = scannerObject.nextInt();
        final int zoomValue = scannerObject.nextInt();

        try {
            final Rectangle rectangleZoomed = rectangles.get(zoomLocate);
            System.out.println("Before: " + rectangleZoomed);

            rectangleZoomed.zoomSize(zoomValue);

            System.out.println("After: " + rectangleZoomed);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("존재하지 않는 배열의 원소를 접근했습니다. java.lang.IndexOutOfBoundsException: Index: " + zoomLocate
                    + ", Size: " + rectangles.size());
        }

    }

    private void showAll() {
        for (Rectangle rectangleShown : rectangles) {
            System.out.println(rectangleShown);
        }
    }

}
