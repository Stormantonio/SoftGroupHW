package javaHW2.randomPrize.storage;

import javaHW2.randomPrize.model.Employee;

import java.io.IOException;

/**
 * Created by Anton on 31.01.2017.
 */
public interface Storage {
    void save(Employee employee);

    void getAll();

    int size();

    void dateOfSalary(String date) throws IOException;

    int getPrize() throws IOException;
}
