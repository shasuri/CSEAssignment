package lab8;

public class Circle implements MyComparable {
    private final Point center;
    private final int radius;

    public Circle(final Point center, final int radius) {
        this.center = center;
        this.radius = radius;
    }

    public int compareTo(final MyComparable other) {
        final long thisSize = this.getSize();
        final long otherSize = other.getSize();

        int compareResult = 0;

        if (thisSize < otherSize)
            compareResult = -1;

        if (thisSize == otherSize)
            compareResult = 0;

        if (thisSize > otherSize)
            compareResult = 1;

        return compareResult;
    }

    public long getSize() {
        final double area = Math.round(Math.pow(radius, 2) * Math.PI);

        final long size = (long) area;

        return size;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        sb.append(this.center.toString());
        sb.append(' ');
        sb.append(this.radius);
        sb.append(' ');
        sb.append(this.getSize());
        sb.append(']');

        return sb.toString();
    }
}