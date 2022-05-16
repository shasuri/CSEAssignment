package lab12;

public class Rectangle {
    private final int width, height;
    private final String name;
    private static int id = 0;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;

        name = String.format("rect[%2d]", id);
        id++;
    }

    public int getArea() {
        return width * height;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return String.format("%s: %5d, %5d", name, width, height);
    }
}