package javaHW3.jewelry;

/**
 * Created by Anton on 08.02.2017.
 * В Полтаве успешно работает ювелирный магазин, так как к магазину постоянно приходят клиенты.
 * Условием работы магазина является наличие 4 клиентов, также одновременно в помещении может находиться
 * не более 5 человек (покупателей). Если в магазине становится меньше 4 покупателей магазин закрывается
 * на перерыв(10 секунд). Каждый покупатель находится в магазине в промежутке от 1 до 8 сек.
 */

public class JewelryShop {
    public static void main(String[] args) {
        Shop shop = new Shop();
        new Thread(new ClientAtTheShop(shop)).start();
        new Thread(new ClientInTheShop(shop)).start();
    }

    private static class Shop {
        static int n = 0;
        int capacity = 0;

        synchronized void atTheShop() throws InterruptedException {
            while (capacity != 4) {
                capacity++;
                Thread.sleep((int) (Math.random() * 1000 + 400));
                System.out.println("Клиент № " + capacity + " подошел к магазину");
                notify();
            }
            wait();
        }

        synchronized void inTheShop() throws InterruptedException {
            capacity = 0;
            for (int i = 1; i < 6; i++) {
                Thread.sleep((int) (Math.random() * 100 + 50));
                System.out.println("Клиент № " + i + " вошел в магазин");
                capacity++;
            }
            while (capacity >= 4) {
                Thread.sleep((int) (Math.random() * 8000 + 1000));
                System.out.println("Клиент № " + randomNum(5, 1) + " ушел из магазина");
                --capacity;
                notify();
            }
            capacity = 0;
            n = 0;
            System.out.println("Магазин закрывается на перерыв. Все клиетны уходят.");
            Thread.sleep(10000);
            wait();
        }

        int randomNum(int x, int y) {
            int num = (int) (Math.random() * x + y);
            if (n == 0) {
                n = num;
            } else if (n == num) {
                num = randomNum(x, y);
            }
            return num;
        }
    }

    private static class ClientAtTheShop implements Runnable {
        Shop shop;

        ClientAtTheShop(Shop shop) {
            this.shop = shop;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 50; i++) {
                    shop.atTheShop();
                }
            } catch (InterruptedException ignored) {
            }
        }
    }

    private static class ClientInTheShop implements Runnable {
        Shop shop;

        ClientInTheShop(Shop shop) {
            this.shop = shop;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 50; i++) {
                    shop.inTheShop();
                }
            } catch (InterruptedException ignored) {
            }
        }
    }
}
