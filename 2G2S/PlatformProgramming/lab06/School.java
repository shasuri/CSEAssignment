package lab6;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class School {
    private final String name;
    private List<Student> students = new ArrayList<>();

    public School(final String name) {
        this.name = name;
    }

    public void addStudent(final Student newStudent) {
        students.add(newStudent);
    }

    public String getSchoolName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public boolean match(final String matchingName) {
        return Objects.equals(matchingName, name);
    }

    public String toString() {
        String msg = "School Name: " + name + " Student Count: " + students.size() + "\n";

        for (Student printStudent : students) {
            if (printStudent == null) {
                ;
                break;
            }

            msg += printStudent.toString();
        }

        return msg;
    }
}