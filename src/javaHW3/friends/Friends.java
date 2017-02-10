package javaHW3.friends;

/**
 * Created by Anton on 09.02.2017.
 * Друзья собираются каждый понедельник(10 сек - неделя) поиграть в футбол.
 * Если собирается 10 человек и на улице не идет дождь, матч проходит успешно, иначе переносится на следующую неделю.
 */

public class Friends {
    private static boolean isRun = true;
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Week()).start();
        for (int i = 1; i < 11; i++) {
            if (isRun) {
                new Thread(new Friend(i)).start();
                count++;
                Thread.sleep((int) (Math.random() * 1100 + 500));
            }
        }
    }

    private static boolean isRaining() {
        return (int) (Math.random() * 2 + 0) == 0;
    }

    private static class Friend implements Runnable {
        int friendNumber;

        Friend(int friendNumber) {
            this.friendNumber = friendNumber;
        }

        @Override
        public void run() {
            System.out.println("Игрок " + friendNumber + " пришел поиграть");
        }
    }

    private static class Week implements Runnable {
        @Override
        public void run() {
            try {
                String[] dayOfWeek = {"Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};
                for (String s : dayOfWeek) {
                    System.out.println(s + "...");
                    Thread.sleep(1666);
                }
                System.out.println("Понедельник...");
                Thread.sleep(4);
                isRun = false;
                if (count == 10) {
                    if (!isRaining()) {
                        System.out.println("Собралось " + count + " друзей. Матч прошел успешно!");
                    } else {
                        System.out.println("Собралось " + count + " друзей, но на улице идет дождь... Матч переносится через неделю!");
                    }
                } else {
                    System.out.println("Собралось " + count + " друзей. Матч переносится через неделю!");
                }
            } catch (InterruptedException ignored) {
            }
        }
    }
}
