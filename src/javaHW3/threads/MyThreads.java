package javaHW3.threads;

import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;
import java.util.jar.Attributes;

/**
 * Created by Asus on 07.02.2017.
 */
public class MyThreads {
    public static int i = 1;

    public static void main(String[] args) throws InterruptedException {
Thread thread = Thread.currentThread();
        thread.setPriority(10);
//        thread.join();
//        Semaphore semaphore = new Semaphore(2, false);



//            Thread th1 = new Thread((Runnable) threads(semaphore));
        threads().arrive();
      /*  th1.start();
        th1.join();*/
//        Thread.currentThread().join();
//            Thread th  = new Thread(new PhaseThread(semaphore, "Thread ", 5));
        /*th.start();
        th.join();*/
        threads().wait();
        thread.join();


    }

    public static Phaser threads() throws InterruptedException {
        Phaser phaser = new Phaser(3);
        new Thread(new PhaseThread(phaser.arrive(), phaser, "Thread ", i)).start();
        new Thread(new PhaseThread(phaser.arriveAndAwaitAdvance(), phaser, "Thread ", ++i)).start();
        new Thread(new PhaseThread(phaser.arrive(), phaser, "Thread ", ++i)).start();
        return phaser;
    }

    public static Phaser threads1() {
        Phaser phaser1 = new Phaser(1);
        new Thread(new PhaseThread(phaser1.arrive(), phaser1, "Thread ", 5)).start();
        return phaser1;
    }
}

class CommonResource {
    int count = 0;

    public CommonResource(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

class PhaseThread implements Runnable {
//    Semaphore semaphore;
    int count = 0;
    int commonResources;
    Phaser phaser;
    String name;
    int threadNumber;

    CommonResource c = new CommonResource(0);

    public PhaseThread(int commonResources, Phaser phaser, String name, int threadNumber) {
//        this.semaphore = semaphore;
        this.commonResources = commonResources;
        this.phaser = phaser;
        this.name = name;
        this.threadNumber = threadNumber;
    }

    public PhaseThread(String name, int threadNumber) {
//        this.semaphore = semaphore;
        this.name = name;
        this.threadNumber = threadNumber;
    }

    public void run() {
        try {
            int i = 0;
            System.out.println(name + threadNumber + " started");
            Thread.sleep(runner());
            System.out.println(name + threadNumber + " ended");
            phaser.arriveAndDeregister();
//            c.setCount(i++);
            count++;
            if (count == 2) {
                Thread.currentThread().join();
            }
            phaser.arriveAndAwaitAdvance();

            /*if (c.getCount() == 2) {
                Phaser p = new Phaser(1);
                new Thread(new PhaseThread(p.arrive(), p, "Thread ", 123));
            }*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int runner() {
        int i = 50000;
        return i / ((int) (Math.random() * 100 + 20));
    }
}
