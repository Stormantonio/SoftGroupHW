package javaHW2.serializeCar;


import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Anton on 29.01.2017.
 * Создать класс “машина” с примитивными и ссылочными полями. Сериализовать и десериализовать этот объект.
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
    private String getSeparator() {
        return System.getProperty("file.separator");
    }

    private void serialize(Car cfs) throws IOException {
        FileOutputStream fos = new FileOutputStream("src" + getSeparator() + "javaHW2" + getSeparator() + "serializeCar" + getSeparator() + "carInfo");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(cfs);
        oos.flush();
        oos.close();
    }

    private Car deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("src" + getSeparator() + "javaHW2" + getSeparator() + "serializeCar" + getSeparator() + "carInfo");
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
            Logger.getLogger(SerializeCar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
