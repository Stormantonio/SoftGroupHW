package javaHW3.threads;

import java.util.concurrent.Phaser;

/**
 * Created by Anton on 07.02.2017.
 * Стартует первый поток(1), после завершения выполнения первого потока стартуют одновременно два потока(2 и 3).
 * После завершения потоков 2 и 3(в зависимости от того кто завершит свое выполнение последний) стартует 4 поток.
 * Время выполнения каждого потока каждый раз разное
 */

public class MyThreads {
    private static int i = 1;
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        boolean a = true;
        while (a) {
            switch (count) {
                case 0:
                    threads().isTerminated();
                    break;
                case 3:
                    threads1();
                    a = false;
                    break;
            }
        }
    }

    private static Phaser threads() throws InterruptedException {
        Phaser phaser = new Phaser(3);
        new Thread(new PhaseThread(phaser.arrive(), phaser, "Thread ", i)).start();
        new Thread(new PhaseThread(phaser.arriveAndAwaitAdvance(), phaser, "Thread ", ++i)).start();
        new Thread(new PhaseThread(phaser.arrive(), phaser, "Thread ", ++i)).start();
        return phaser;
    }

    private static Phaser threads1() {
        Phaser phaser1 = new Phaser(1);
        new Thread(new PhaseThread(phaser1.arrive(), phaser1, "Thread ", 5)).start();
        return phaser1;
    }

    private static class PhaseThread implements Runnable {
        int commonResources;
        Phaser phaser;
        String name;
        int threadNumber;

        PhaseThread(int commonResources, Phaser phaser, String name, int threadNumber) {
            this.commonResources = commonResources;
            this.phaser = phaser;
            this.name = name;
            this.threadNumber = threadNumber;
        }

        public void run() {
            try {
                System.out.println(name + threadNumber + " started");
                Thread.sleep((int) (Math.random() * 3000 + 500));
                count++;
                phaser.arriveAndDeregister();
                System.out.println(name + threadNumber + " ended");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
