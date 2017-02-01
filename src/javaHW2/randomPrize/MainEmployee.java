package javaHW2.randomPrize;

import javaHW2.randomPrize.model.Employee;
import javaHW2.randomPrize.storage.ArrayEmployee;
import javaHW2.randomPrize.storage.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Anton on 31.01.2017.
 */
public class MainEmployee {
    private final static Storage EMPLOYEES = new ArrayEmployee();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Employee e;
        while (true) {
            System.out.println("Enter: 1 - new employer | 2 - list | 3 - day of salary | 4- exit");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            String s = null;

            switch (params[0]) {
                case "1":
                    System.out.println("Enter the name and surname of the employee...");
                    s = reader.readLine();
                    e = new Employee(s);
                    EMPLOYEES.save(e);
                    toConsole();
                    break;
                case "2":
                    toConsole();
                    break;
                case "3":
                    System.out.println("Enter the day of salary:");
                    s = reader.readLine();
                    EMPLOYEES.dateOfSalary(s);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Wrong command!");
                    break;
            }
        }
    }
    private static void toConsole() {
        EMPLOYEES.getAll();
    }
}
