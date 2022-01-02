package lab8;

public class Line implements MyComparable {
    private final Point point1, point2;

    public Line(final Point point1, final Point point2) {
        this.point1 = point1;
        this.point2 = point2;
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

        final double distance = Math.round(Math.sqrt(Math.pow(this.point1.getX() - this.point2.getX(), 2)
                + Math.pow(this.point1.getY() - this.point2.getY(), 2)));

        final long size = (long) distance;

        return size;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        sb.append(this.point1.toString());
        sb.append(' ');
        sb.append(this.point2.toString());
        sb.append(' ');
        sb.append(this.getSize());
        sb.append(']');

        return sb.toString();
    }
}