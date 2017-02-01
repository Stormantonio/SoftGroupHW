package javaHW2;

import onlyMine.TestSerializable;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Anton on 29.01.2017.
 */
public class Car implements Serializable {
    private String brand;
    private int doors;
    private boolean isSportCar;

    public Car(String brand, int doors, boolean isSportCar) {
        this.brand = brand;
        this.doors = doors;
        this.isSportCar = isSportCar;
    }

    @Override
    public String toString() {
        return "Car{" + "brand='" + brand + '\'' + ", doors=" + doors + ", isSportCar=" + isSportCar + '}';
    }
}

class SerializeCar {
    private void serialize(Car cfs) throws IOException {
        FileOutputStream fos = new FileOutputStream("src//javaHW2//CarInfo.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(cfs);
        oos.flush();
        oos.close();
    }

    public Car deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("src//javaHW2//CarInfo.txt");
        ObjectInputStream oin = new ObjectInputStream(fis);
        return (Car) oin.readObject();
    }

    public static void main(String[] args) {
        SerializeCar serializeCar = new SerializeCar();
        Car car1 = new Car("Toyota", 4, false);
        try {
            serializeCar.serialize(car1);
            System.out.println(serializeCar.deserialize());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TestSerializable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
