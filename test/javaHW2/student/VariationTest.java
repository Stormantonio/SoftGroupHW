package javaHW2.student;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Anton on 31.01.2017.
 */
public class VariationTest {
    private List<Student> students = new ArrayList<>();

    private static final String NAME_1 = "Anton Chernenko";
    private static final String NAME_2 = "Andrey Mironov";
    private static final String NAME_3 = "Evgeniy Chaika";
    private static final String NAME_4 = "Kirill Sashko";
    private static final String NAME_5 = "Victoria Harlanchuk";
    private static final String NAME_6 = "Lilia Chernenko";
    private static final String NAME_7 = "Ihor Mykytenko";
    private static final String NAME_8 = "Vasiliy Alibababev";
    private static final String NAME_9 = "Vasislisa Prekrasnaya";
    private static final String NAME_10 = "Alexey Alexeev";

    private static final int COURSE_1 = 1;
    private static final int COURSE_2 = 2;
    private static final int COURSE_3 = 3;
    private static final int COURSE_4 = 4;
    private static final int COURSE_5 = 5;

    private static final Student STUDENT_1;
    private static final Student STUDENT_2;
    private static final Student STUDENT_3;
    private static final Student STUDENT_4;
    private static final Student STUDENT_5;
    private static final Student STUDENT_6;
    private static final Student STUDENT_7;
    private static final Student STUDENT_8;
    private static final Student STUDENT_9;
    private static final Student STUDENT_10;

    static {
        STUDENT_1 = new Student(NAME_1, COURSE_1);
        STUDENT_2 = new Student(NAME_2, COURSE_2);
        STUDENT_3 = new Student(NAME_3, COURSE_1);
        STUDENT_4 = new Student(NAME_4, COURSE_3);
        STUDENT_5 = new Student(NAME_5, COURSE_5);
        STUDENT_6 = new Student(NAME_6, COURSE_4);
        STUDENT_7 = new Student(NAME_7, COURSE_1);
        STUDENT_8 = new Student(NAME_8, COURSE_5);
        STUDENT_9 = new Student(NAME_9, COURSE_1);
        STUDENT_10 = new Student(NAME_10, COURSE_3);
    }

    @Test
    public void printStudents() throws Exception {
        int count = 0;
        students.clear();
        students.add(STUDENT_1);
        students.add(STUDENT_2);
        students.add(STUDENT_3);
        students.add(STUDENT_4);
        students.add(STUDENT_5);
        students.add(STUDENT_6);
        students.add(STUDENT_7);
        students.add(STUDENT_8);
        students.add(STUDENT_9);
        students.add(STUDENT_10);
        int course = COURSE_1;
        for (Iterator it = students.iterator(); it.hasNext(); ) {
            Student student = (Student) it.next();
            if (student.getCourse() == course) {
                System.out.println(student.getName());
                count++;
            }
        }
        Assert.assertEquals(4, count);
    }
}
