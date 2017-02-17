package javaHW4.trucksAndParcels;

/**
 * Created by Anton on 16.02.2017.
 * В А: 5 грузовиков, 5 посылок, в Б 10 грузовиков и 10 посылок. Время грузовиков в пути 2 сек.
 * Стартуют одновременно из А и Б. Потом грузовики смотрят есть ли в наличии посылки  и отвозят их снова (возят  туда-сюда).
 * Мы должны знать в каком пункте в данный момент находится грузовик (если в А, то берёт посылки с А, если в Б, то с Б).
 * Добавить:
 * - разное время в дороге из А в Б и из Б в А (1 и 3 сек)
 * - разное время + в А 1 посылка (5 грузовиков), в Б 10 посылок
 * (если есть посылка - едет, если нет - ожидает(пока не привезут другие посылки))
 */

public class Main {
    //    public static int shipmentACount = 1; // + в А 1 посылка (5 грузовиков), в Б 10 посылок
    private static int shipmentACount = 5;
    private static int shipmentBCount = 10;
    private static String[] shipments = {"A", "B"};
    private static String[] marks = {"SCANIA", "MAN"};
    private static int[] speedTruck = {2000, 2000};
//    public static int[] speedTruck = {1000, 3000}; // разное время в дороге из А в Б и из Б в А (1 и 3 сек)

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            new Thread(new Truck(i, speedTruck[0], shipments[0], shipments[1], marks[0])).start();
        }
        for (int i = 1; i <= 10; i++) {
            new Thread(new Truck(i, speedTruck[1], shipments[1], shipments[0], marks[1])).start();
        }
    }

    private static class Truck implements Runnable {
        private int numberTruck;
        private int speed;
        private String startPlace;
        private String endPlace;
        private int parcels;
        private int left;
        private int become;
        private String mark;

        Truck(int numberTruck, int speed, String startPlace, String endPlace, String mark) {
            this.numberTruck = numberTruck;
            this.speed = speed;
            this.startPlace = startPlace;
            this.endPlace = endPlace;
            this.mark = mark;
        }

        void setSpeed(int speed) {
            this.speed = speed;
        }

        int getParcels() {
            if (startPlace.equals(shipments[0])) {
                parcels = shipmentACount;
            } else {
                parcels = shipmentBCount;
            }
            return parcels;
        }

        synchronized String getMark() {
            return mark;
        }

        void sortShipments() {
            if (startPlace.equals(shipments[0])) {
                startPlace = shipments[1];
                endPlace = shipments[0];
            } else {
                startPlace = shipments[0];
                endPlace = shipments[1];
            }
        }

        private void variationSpeed() {
            if (startPlace.equals(shipments[0])) {
                this.setSpeed(speedTruck[0]);
            } else {
                this.setSpeed(speedTruck[1]);
            }
        }

        private void expecting() {
            try {
                System.out.println("В пункте " + startPlace + " нет посылок. Грузовик " + getMark() + " № " + numberTruck + " ожидает...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized int isLeft() {
            if (startPlace.equals(shipments[0])) {
                if (shipmentACount > 0) {
                    left = --shipmentACount;
                } else {
                    expecting();
                }
            } else {
                if (shipmentBCount > 0) {
                    left = --shipmentBCount;
                } else {
                    expecting();
                }
            }
            return left;
        }

        synchronized int isBecome() {
            if (startPlace.equals(shipments[0])) {
                if (shipmentBCount >= 0) {
                    become = ++shipmentBCount;
                }
            } else {
                if (shipmentACount >= 0) {
                    become = ++shipmentACount;
                }
            }
            return become;
        }

        @Override
        public synchronized void run() {
            try {
                getParcels();
                while (true) {
                    System.out.println("Грузовик " + getMark() + " № " + numberTruck + " выехал из пункта " + startPlace + " в пункт " + endPlace + ". " +
                            "Пункт " + startPlace + " : посылок - " + isLeft() + " шт.");
                    Thread.sleep(speed);
                    System.out.println("Грузовик " + getMark() + " № " + numberTruck + " приехал в пункт " + endPlace + " из пункта " + startPlace + ". " +
                            "Пункт " + endPlace + " : посылок стало - " + isBecome() + " шт.");
                    sortShipments();
                    variationSpeed();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
