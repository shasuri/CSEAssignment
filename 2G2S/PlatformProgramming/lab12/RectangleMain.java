package lab12;

import java.util.ArrayList;
import java.util.List;

public class RectangleMain {
    public static void main(String[] args) {
        List<Rectangle> list0 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Rectangle r = new Rectangle(i + 10, i + 10);
            list0.add(r);
        }
        List<Rectangle> list1 = new ArrayList<>();
        for (Rectangle r : list0) {
            if (r.getArea() >= 200)
                list1.add(r);

        }
        List<Rectangle> list2 = new ArrayList<>();
        for (Rectangle r : list1) {
            if (r.getWidth() % 2 == 0)
                list2.add(r);
        }
        List<String> list3 = new ArrayList<>();
        for (Rectangle r : list2) {
            list3.add(r.getName().toUpperCase());
        }
        for (String s : list3) {
            System.out.println(s);
        }
    }
}
