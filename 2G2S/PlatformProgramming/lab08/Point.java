package lab8;

public class Point {
    private final int x, y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append('[');
        sb.append(this.getX());
        sb.append(", ");
        sb.append(this.getY());
        sb.append(']');

        return sb.toString();
    }
}