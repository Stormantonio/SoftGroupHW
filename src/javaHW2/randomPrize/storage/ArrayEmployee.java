package javaHW2.randomPrize.storage;

import javaHW2.randomPrize.model.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

/**
 * Created by Anton on 31.01.2017.
 */
public class ArrayEmployee implements Storage {
    private List<Employee> employees = new ArrayList<>();

    @Override
    public void save(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
            System.out.println("Employer " + employee + " successfully added!");
        } else System.out.println("Name already exists!");
    }

    @Override
    public void getAll() {
        employees.forEach(System.out::println);
    }

    @Override
    public int size() {
        return employees.size();
    }

    @Override
    public void dateOfSalary(String date) throws IOException {
        if (employees.isEmpty()) {
            System.out.println("There is no employees!");
            return;
        }
        if (date.length() > 2) {
            System.out.println("Wrong date!");
            return;
        }
        if (date.length() == 1) {
            date = +0 + date;
        }
        if (date.equals(today())) {
            System.out.println("Employer " + employees.get(randomIndex()) + " got prize " + getPrize() + " $");
        } else {
            System.out.println("Today is no salary!");
        }
    }

    @Override
    public int getPrize() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the prize!");
        return parseInt(reader.readLine());
    }

    private int randomIndex() {
        Random random = new Random();
        return random.nextInt(size());
    }

    private String today() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
}
