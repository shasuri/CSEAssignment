package lab9;

public class Rectangle {
    private int width, height;
    // private float area;

    public Rectangle(final int width, final int height) {
        this.width = width;
        this.height = height;
        // this.area = (float) (this.width * this.height);
    }

    public void zoomSize(final int zoomValue) {
        width = zoomValue * width;
        height = zoomValue * height;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Rectangle: width ");
        sb.append(width);
        sb.append(", ");
        sb.append(height);

        return sb.toString();
    }
    // Rectangle: width 20, height 20
}
