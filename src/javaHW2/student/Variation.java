package javaHW2.student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Anton on 31.01.2017.
 */
public class Variation {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void printStudents(List list, int course) {
        System.out.println("There are students of the " + course + " course:");
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Student student = (Student) it.next();
            if (student.getCourse() == course) {
                System.out.println(student.getName());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        List<Student> students = new ArrayList<>();
        Variation variation = new Variation();
        students.add(new Student("Anton Chernenko", 5));
        students.add(new Student("Andrey Voloshyn", 2));
        students.add(new Student("Olga Ivanova", 1));
        students.add(new Student("Vladimir Petrov", 2));
        students.add(new Student("Kirill Sashko", 1));
        students.add(new Student("Inna Petrenko", 5));
        students.add(new Student("Elena Prihodko", 3));
        students.add(new Student("Victoria Harlanchuk", 3));
        students.add(new Student("Alexey Alexeev", 1));
        students.add(new Student("Evgeniy Chaika", 5));
        students.add(new Student("Vasilisa Prekrasnaya", 5));

        System.out.println("Enter the course, please:");
        int course = Integer.parseInt(reader.readLine());
        variation.printStudents(students, course);

    }
}