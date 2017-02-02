package javaHW2.employees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Anton on 31.01.2017.
 * Есть список сотрудников, которые есть имя фамилия и уникальный номер сотрудника.
 * Получить сотрудника по уникальному ключу.
 */

class Employee {
    private String name;
    private String surname;

    Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    String getSurname() {
        return surname;
    }
}

class Main {
    private static int uuid = 0;
    private static Map<Integer, Employee> mapEmployees = new HashMap<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("Enter command: 1 - addNode new employee | 2 - get employee by uuid | 3 - get all employees | 4 - exit");
            String command = reader.readLine();
            switch (command) {
                case "1":
                    addEmployee();
                    break;
                case "2":
                    getEmployee();
                    break;
                case "3":
                    getAll();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Wrong command!");
                    break;
            }
        }
    }

    private static void addEmployee() throws IOException {
        System.out.println("Enter name:");
        String name = reader.readLine();
        System.out.println("Enter surname:");
        String surname = reader.readLine();
        addUuid();
        mapEmployees.put(uuid, new Employee(name, surname));
    }

    private static void addUuid() throws IOException {
        System.out.println("Enter uuid");
        uuid = Integer.parseInt(reader.readLine());
        Set<Map.Entry<Integer, Employee>> set = mapEmployees.entrySet();
        for (Map.Entry<Integer, Employee> me : set) {
            if (uuid == me.getKey()) {
                System.out.println("This uuid is already exists! Enter uuid");
                addUuid();
            }
        }
    }

    private static void getEmployee() throws IOException {
        System.out.println("Enter uuid:");
        int findByUuid = Integer.parseInt(reader.readLine());
        Set<Map.Entry<Integer, Employee>> set = mapEmployees.entrySet();
        for (Map.Entry<Integer, Employee> me : set) {
            if (findByUuid == me.getKey()) {
                System.out.println(me.getValue().getName() + " " + me.getValue().getSurname());
                return;
            }
            System.out.println("There is no employee with this uuid!");
        }
    }

    private static void getAll() {
        Set<Map.Entry<Integer, Employee>> set = mapEmployees.entrySet();
        for (Map.Entry<Integer, Employee> me : set) {
            System.out.println(me.getValue().getName() + " " + me.getValue().getSurname());
        }
    }
}
