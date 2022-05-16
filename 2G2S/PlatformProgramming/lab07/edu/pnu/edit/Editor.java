package edu.pnu.edit;

import java.util.ArrayList;
import java.util.List;

import edu.pnu.shape.Circle;
import edu.pnu.shape.Rectangle;

public class Editor {
    private List<Object> shapes = new ArrayList<>();

    public void add(final Object newShape) {
        if (!((newShape instanceof Rectangle) || (newShape instanceof Circle)))
            return;

        shapes.add(newShape);
    }

    public void clear() {
        shapes.clear();
    }

    public void list() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');

        int i = 1;

        for (Object shapeElem : shapes) {
            sb.append(shapeElem);

            if (i < shapes.size())
                sb.append(", ");
            i++;
        }

        sb.append(']');

        System.out.println(sb);

    }
}