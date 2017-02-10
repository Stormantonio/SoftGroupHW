package javaHW3.apples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Anton on 07.02.2017.
 * Есть склад, на котором хранятся яблока (сорт, количество). Поставщик периодически привозит на склад яблока.
 * Два магазина продают яблока из этого склада. Каждый из магазинов организовывает доставку в свой магазин.
 * Когда на складе заканчивается товар, магазины простаивают. Первый магазин продает в 2 раза больше яблок,
 * чем второй соответственно в два раза чаще обращается на склад. Организовать работу магазинов.
 */

public class Main {
    public static void main(String[] args) {
        Shop shop1 = new Shop(1);
        Shop shop2 = new Shop(2);
        Thread tpShop1 = new Thread(new ShopPurchase(shop1));
        tpShop1.setPriority(1);
        tpShop1.start();
        Thread tsShop1 = new Thread(new ShopSale(shop1));
        tsShop1.setPriority(1);
        tsShop1.start();
        Thread tpShop2 = new Thread(new ShopPurchase(shop2));
        tpShop2.setPriority(10);
        tpShop2.start();
        Thread tsShop2 = new Thread(new ShopSale(shop2));
        tsShop2.setPriority(10);
        tsShop2.start();
    }

    private static class Shop extends AppleStock {
        private static int capacity = 0;
        private ReentrantLock reentrantLock;
        private Condition condition;
        int number;

        Shop(int number) {
            this.reentrantLock = new ReentrantLock();
            this.condition = reentrantLock.newCondition();
            this.number = number;
        }

        int getNumber() {
            return number;
        }

        void someMethod() {
            numberElem = (int) (Math.random() * 5 + 0);
            int val = (int) (Math.random() * 130 + 60);

            if (apples.get(numberElem).getValue() >= val) {
                apples.get(numberElem).setValue(apples.get(numberElem).getValue() - val);
                buy = val;
                stockCapacity -= buy;
                capacity += buy;
                System.out.println("Магазин № " + getNumber() + ": новый завоз: " + buy + " кг яблок " + apples.get(numberElem).getSort());
                System.out.println("На складе " + stockCapacity + " кг яблок");
            } else if (apples.get(numberElem).getValue() < val && apples.get(numberElem).getValue() != 0) {
                buy = apples.get(numberElem).getValue();
                apples.get(numberElem).setValue(0);
                stockCapacity -= buy;
                capacity += buy;
                System.out.println("Магазин № " + getNumber() + ": новый завоз: " + buy + " кг яблок " + apples.get(numberElem).getSort());
                System.out.println("Яблоки сорта " + apples.get(numberElem).getSort() + " закночились на складе!");
                System.out.println("На складе " + stockCapacity + " кг яблок");
            } else {
                System.out.println("Яблок сорта " + apples.get(numberElem).getSort() + " пока нет на складе!");
            }
        }

        void bringApples() throws InterruptedException {
            try {
                reentrantLock.lock();
                while (stockCapacity == 0) {
                    System.out.println("Склад пуст!");
                    System.out.println("Простой магазинов...");
                    Thread.sleep(3000);
                    System.out.println("Новый завоз товара на склад...");
                    addApples();
                    stockCapacity = 650;
                }
                someMethod();
                condition.signalAll();
            } finally {
                reentrantLock.unlock();
            }
        }

        void sellApples() throws InterruptedException {
            try {
                reentrantLock.lock();
                int val = (int) (Math.random() * 130 + 60);
                if (val <= capacity) {
                    capacity -= val;
                    System.out.println("Магазин № " + getNumber() + ": продано " + val + " кг яблок сорта " + getAppleSort());
                } else if (val > capacity && capacity != 0) {
                    System.out.println("Магазин № " + getNumber() + ": продано " + capacity + " кг яблок сорта " + getAppleSort() + ". В магазине нету яблок сорта " + getAppleSort() + ".");
                    capacity = 0;
                } else {
                    condition.await();
                }
                condition.signalAll();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    private static class ShopPurchase implements Runnable {
        private Shop shop;

        ShopPurchase(Shop shop) {
            this.shop = shop;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    shop.bringApples();
                    Thread.sleep(3000 * shop.getNumber());
                }
            } catch (InterruptedException ignored) {
            }
        }
    }

    private static class ShopSale implements Runnable {
        private Shop shop;

        ShopSale(Shop shop) {
            this.shop = shop;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    shop.sellApples();
                    Thread.sleep(3000 / shop.getNumber());
                }
            } catch (InterruptedException ignored) {
            }
        }
    }

    static class AppleStock {
        static int stockCapacity = 650;
        static int buy = 0;
        static int numberElem = 0;
        static List<Apples> apples = new ArrayList<>();

        static {
            apples.add(new Apples("ГРУШОВКА МОСКОВСКАЯ", 130));
            apples.add(new Apples("КВИНТИ", 130));
            apples.add(new Apples("МАНТЕТ", 130));
            apples.add(new Apples("АНТОНОВКА", 130));
            apples.add(new Apples("МАКИНТОШ", 130));
        }

        static void addApples() {
            for (Apples a : apples) {
                a.setValue(130);
            }
        }

        String getAppleSort() {

            return apples.get(numberElem).getSort();
        }
    }

    private static class Apples {
        private String sort;
        private int value;

        Apples(String sort, int value) {
            this.sort = sort;
            this.value = value;
        }

        String getSort() {
            return sort;
        }

        int getValue() {
            return value;
        }

        void setValue(int value) {
            this.value = value;
        }
    }
}
