package javaHW3.auction;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Asus on 07.02.2017.
 * В автосалоне аукцион.
 * Условием начала аукциона является наличие 5 автомобилей в салоне, также 10 покупателей возле магазина.
 */
public class MyAuction {
    private static final CountDownLatch START = new CountDownLatch(8);

    public static void main(String[] args) {

    }

    public static class Car implements Runnable {
        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
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
}

