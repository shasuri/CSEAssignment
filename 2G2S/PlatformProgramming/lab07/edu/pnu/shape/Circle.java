package edu.pnu.shape;

public class Circle {
    private Point center;
    private int radius;
    private float area;

    public Circle(final Point center, final int radius) {
        this.center = center;
        this.radius = radius;
        this.area = (float) (Math.pow(this.radius, 2) * 3.14);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ Circle ");
        sb.append(center);
        sb.append(' ');
        sb.append(radius);
        sb.append(' ');
        sb.append(String.format("%.6f", area));
        sb.append(']');

        return sb.toString();
    }
}