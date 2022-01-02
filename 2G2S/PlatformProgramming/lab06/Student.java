package lab6;

import java.util.Objects;

public class Student {
    private final String name;
    private int year;
    private final School theSchool;

    public Student(final School theSchool, final String name, final int year) {
        this.theSchool = Objects.requireNonNull(theSchool);
        this.name = Objects.requireNonNull(name);
        this.year = Objects.requireNonNull(year);
    }

    public boolean match(final String matchingName, final int matchingYear) {
        return Objects.equals(matchingName, name) && Objects.equals(matchingYear, year);
    }

    public String toString() {
        return "[Name: " + name + ", School:" + theSchool.getSchoolName() + ", " + year + "학년]";

    }
}