package javaHW2.student;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
        STUDENT_2 = new Student(NAME_2, COURSE_1);
        STUDENT_3 = new Student(NAME_3, COURSE_1);
        STUDENT_4 = new Student(NAME_4, COURSE_1);
        STUDENT_5 = new Student(NAME_5, COURSE_1);
        STUDENT_6 = new Student(NAME_6, COURSE_1);
        STUDENT_7 = new Student(NAME_7, COURSE_1);
        STUDENT_8 = new Student(NAME_8, COURSE_1);
        STUDENT_9 = new Student(NAME_9, COURSE_1);
        STUDENT_10 = new Student(NAME_10, COURSE_1);
    }

    @Before
    public void setUp() throws Exception {
        this.students.clear();
        this.students.add(STUDENT_1);
        this.students.add(STUDENT_2);
        this.students.add(STUDENT_3);
        this.students.add(STUDENT_4);
        this.students.add(STUDENT_5);
        this.students.add(STUDENT_6);
        this.students.add(STUDENT_7);
        this.students.add(STUDENT_8);
        this.students.add(STUDENT_9);
        this.students.add(STUDENT_10);
    }

    @Test
    public void printStudents() throws Exception {
        Assert.assertEquals(10, students.size());
    }
}
