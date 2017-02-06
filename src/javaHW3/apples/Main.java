package javaHW3.apples;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Anton on 07.02.2017.
 */
public class Main {
    public static void main(String[] args) {
        Shop shop1 = new Shop(1);
        Shop shop2 = new Shop(2);
    }

}

class Shop extends AppleStock{
    public int capacity = 0;
    ReentrantLock reentrantLock;
    Condition condition;
    private int number;

    public Shop(int number) {
        this.reentrantLock = new ReentrantLock();
        this.condition = reentrantLock.newCondition();
        this.number = number;

    }

    public int getNumber() {
        return number;
    }

    public void bringApples() throws InterruptedException {
        try {
            reentrantLock.lock();
            if (capacity == 0) {
                System.out.println("Простой магазина №" + getNumber());
                condition.await();
            }
            int value = (int) (Math.random() * 10) + 1;
            stockCapacity -= value;
            stock();
            capacity += value;
            System.out.println("Магазин № " + getNumber() + ": новый завоз - " + value + " кг яблук. Итого: " + capacity + " кг яблук");
            condition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void sellApples(int coef) throws InterruptedException {
        try {
            reentrantLock.lock();
            if (capacity == 0) {
                System.out.println("Простой магазина №" + getNumber());
                condition.await();
            }
            int value = (int) (Math.random() * 10 * coef) + 1;
            if (value <= capacity) {
                capacity -= value;
                System.out.println("Магазин № " + getNumber() + ": продано " + capacity);
            }
            System.out.println("Магазин № " + getNumber() + ": продано " + capacity + ". В магазине нету яблок.");
            capacity = 0;
            condition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }
}

class AppleStock {
    int stockCapacity = 500;
    ReentrantLock stockReentrantLock;
    Condition stockCondition;

    ArrayList<Aplles> aplles = new ArrayList<>();

    public AppleStock() {
        this.stockReentrantLock = new ReentrantLock();
        this.stockCondition = stockReentrantLock.newCondition();
    }

    public void ApplesArray() {
        aplles.add(new Aplles("ГРУШОВКА МОСКОВСКАЯ", 500));
        aplles.add(new Aplles("КВИНТИ", 500));
        aplles.add(new Aplles("МАНТЕТ", 500));
        aplles.add(new Aplles("АНТОНОВКА", 500));
        aplles.add(new Aplles("МАКИНТОШ", 500));
        aplles.add(new Aplles("СПАРТАК", 500));
        aplles.add(new Aplles("МЕДУНИЦА ЗИМНЯЯ", 500));
        aplles.add(new Aplles("РИХАРД", 500));
        aplles.add(new Aplles("КУТУЗОВЕЦ", 500));
        aplles.add(new Aplles("СИНАП", 500));
    }

    // дописать, если какой-то сорт заканчивается...

    public void stock(){
        if (stockCapacity < 100) {
            stockCapacity = 500;
            System.out.println("Новый завоз на склад. " + stockCapacity + " кг яблук");
        }
    }
}

class Aplles {
    String sort;
    int value;

    public Aplles(String sort, int value) {
        this.sort = sort;
        this.value = value;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
