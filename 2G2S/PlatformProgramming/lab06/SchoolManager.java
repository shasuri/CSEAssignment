package lab6;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class SchoolManager {
    private List<School> schools = new ArrayList<>();

    public School findSchool(final String schoolName) {
        final String foundSchoolName = Objects.requireNonNull(schoolName);

        for (School foundSchool : schools) {
            if (foundSchool == null)
                return null;

            if (foundSchool.match(foundSchoolName))
                return foundSchool;
        }

        return null;
    }

    public School createStudent(final String schoolName) {
        final String createdSchoolName = Objects.requireNonNull(schoolName);

        final School createdSchool = new School(createdSchoolName);

        schools.add(createdSchool);

        return createdSchool;
    }

    public List<Student> findStudent(final String studentName, final int schoolYear) {
        final String foundStudentName = Objects.requireNonNull(studentName);
        final int foundSchoolYear = Objects.requireNonNull(schoolYear);

        List<Student> foundStudents = new ArrayList<>();

        for (School foundSchool : schools) {
            if (foundSchool == null)
                break;

            for (Student foundStudent : foundSchool.getStudents()) {
                if (foundStudent == null)
                    break;
                if (foundStudent.match(foundStudentName, foundSchoolYear))
                    foundStudents.add(foundStudent);
            }
        }

        return foundStudents;
    }

    public void removeAllSchools() {
        schools.clear();
    }

    public String toString() {
        String msg = "Total School Count: " + schools.size() + "\n";

        int lastIndex = 0;

        for (School printSchool : schools) {
            if (printSchool == null)
                break;

            msg += printSchool.toString() + "\n";

            if (lastIndex < schools.size() - 1)
                msg += "\n";
            lastIndex += 1;
        }

        return msg;
    }
}
