package edu.pnu.shape;

public class Rectangle {
    private int width, height;
    private float area;

    public Rectangle(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.area = (float) (this.width * this.height);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ Rectangle ");
        sb.append(width);
        sb.append(' ');
        sb.append(height);
        sb.append(' ');
        sb.append(String.format("%.6f", area));
        sb.append(']');

        return sb.toString();
    }
    // [ Rectangle 10 40 400.000000]
}
