package javaHW2.employees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Anton on 31.01.2017.
 */
public class Employee {
    int uuid;
    String name;
    String surname;

    public Employee(int uuid, String name, String surname) {
        this.uuid = uuid;
        this.name = name;
        this.surname = surname;
    }

    public int getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

}

class Main {
    private static int uuid = 0;
    private static List<Employee> employees = new ArrayList<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("Enter command: 1 - add new employee | 2 - get employee by uuid | 3 - exit");
            String command = reader.readLine();
            switch (command) {
                case "1":
                    addEmployee();
                    break;
                case "2":
                    getEmployee();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Wrong command!");
                    break;
            }
        }
    }

    private static void addEmployee() throws IOException {
        System.out.println("Enter the name:");
        String name = reader.readLine();
        System.out.println("Enter the surname:");
        String surname = reader.readLine();
        employees.add(new Employee(uuid++, name, surname));
    }

    private static void getEmployee() throws IOException {
        System.out.println("Enter the uuid:");
        int findByUuid = Integer.parseInt(reader.readLine());
        for (Employee employee : employees) {
            if (employee.getUuid() == findByUuid) {
                System.out.println(employee.getName() + " " + employee.getSurname());
                return;
            }
            System.out.println("There is no employee with this uuid!");
        }
    }
}
