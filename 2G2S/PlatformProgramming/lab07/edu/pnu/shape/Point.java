package edu.pnu.shape;

public class Point {
    private int x, y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        sb.append(x);
        sb.append(", ");
        sb.append(y);
        sb.append(']');

        return sb.toString();
    }
}