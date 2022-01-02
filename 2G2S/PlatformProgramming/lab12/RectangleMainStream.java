package lab12;

import java.util.ArrayList;
import java.util.List;

public class RectangleMainStream {
    public static void main(String[] args) {
        List<Rectangle> list0 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Rectangle r = new Rectangle(i + 10, i + 10);
            list0.add(r);
        }

        list0.stream().filter(r -> r.getArea() >= 200).filter(r -> (r.getWidth() % 2) == 0).map(Rectangle::getName)
                .map(String::toUpperCase).forEach(System.out::println);

    }
}