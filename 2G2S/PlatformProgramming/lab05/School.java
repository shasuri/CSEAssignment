package lab5;

import java.util.Objects;

public class School {
    private String name;
    private int limit;

    private Student[] students;
    private int studentCount;

    public String toString() {
        String msg = "School Name: " + name + " Student Count: " + studentCount + "\n";

        for (int i = 0; i < studentCount; i++) {
            msg += "\t" + students[i] + "\n";
        }
        return msg;
    }

    public School(final String schoolName, final int studentLimit) {
        name = Objects.requireNonNull(schoolName);
        limit = Objects.requireNonNull(studentLimit);
        students = new Student[limit];
        studentCount = 0;
    }

    public void addStudent(final Student newOne) {
        final Student newStudent = new Student(newOne.getName(), newOne.getYear());
        students[studentCount] = newStudent;
        studentCount++;
    }

    public void removeAllStudent() {
        for (int i = 0; i < studentCount; i++) {
            students[i] = null;
        }
        studentCount = 0;
    }

    public Student findStudent(final String studentName, final int schoolYear) {
        for (int i = 0; i < studentCount; i++) {
            if (Objects.equals(students[i].getName(), studentName)
                    && Objects.equals(students[i].getYear(), schoolYear)) {
                return students[i];
            }
        }

        return null;
    }

    public boolean equals(final Object otherSchool) {
        if (!(otherSchool instanceof School))
            return false;
        final var other = (School) otherSchool;

        return Objects.equals(this.hashCode(), other.hashCode());
    }

    public int hashCode() {
        return Objects.hash(name, limit, students, studentCount);
    }

}