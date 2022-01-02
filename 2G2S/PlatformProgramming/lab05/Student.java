package lab5;

import java.util.Objects;

public class Student {
    private String name;
    private int year;

    public Student(final String studentName, final int studentYear) {
        name = Objects.requireNonNull(studentName);
        year = Objects.requireNonNull(studentYear);
    }

    public boolean equals(final Object otherStudent) {
        if (!(otherStudent instanceof Student))
            return false;
        final var other = (Student) otherStudent;

        return Objects.equals(this.hashCode(), other.hashCode());

    }

    public int hashcode() {
        return Objects.hash(name, year);
    }

    public String toString() {
        return "[" + name + ", " + year + "학년]";
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }
}