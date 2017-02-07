package javaHW3.auction;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Asus on 07.02.2017.
 * В автосалоне аукцион.
 * Условием начала аукциона является наличие 5 автомобилей в салоне, также 10 покупателей возле магазина.
 */
public class MyAuction {
    private static final CountDownLatch START = new CountDownLatch(17);
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        int j = 0;
        boolean a = true;
        while (a) {
            switch ((int) (Math.random() * 2 + 1)) {
                case 1:
                    i++;
                    if (i < 6) {
                        new Thread(new Car(i)).start();
                        Thread.sleep(randomTime(2000, 500));
                        break;
                    }
                    if (i >= 6 && j >= 11) {
                        a = false;
                        break;
                    }
                    break;
                case 2:
                    j++;
                    if (j < 11) {
                        new Thread(new Buyer(j, randomTime(5000, 200))).start();
                        Thread.sleep(randomTime(1000, 500));
                        break;
                    }
                    if (i >= 6 && j >= 11) {
                        a = false;
                        break;
                    }
                    break;
            }
        }

        while (START.getCount() > 2) {
            Thread.sleep(100);
        }


        Thread.sleep(1000);
        System.out.println("В саллоне 5 автомобилей, возле магазина 10 покупателей.");
        START.countDown();
        Thread.sleep(1000);
        System.out.println("Начался аукцион...");
        START.countDown();

    }

    private static int randomTime(int x, int y) {
        return (int) (Math.random() * x + y);
    }

    private static class Car implements Runnable {
        private int carNumber;


        Car(int carNumber) {
            this.carNumber = carNumber;
        }

        Car() {
        }

        @Override
        public void run() {
            try {
                System.out.println("Автомобиль № " + carNumber + " появился на аукционе!");
                START.countDown();
                START.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Buyer extends Car implements Runnable {
        private int buyerNumber;
        private int buyingTime;

        Buyer(int buyerNumber, int buyingTime) {
            this.buyerNumber = buyerNumber;
            this.buyingTime = buyingTime;
        }

        @Override
        public void run() {
            try {
                System.out.println("Покупатель № " + buyerNumber + " ожидает возле автосалона...");
                START.countDown();
                START.await();
                while (count < 5) {
                    count++;
                    Thread.sleep(buyingTime);
                    System.out.println("Покупатель № " + buyerNumber + " купил автомобиль!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
