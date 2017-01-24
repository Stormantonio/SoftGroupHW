package javaHW1;

/**
 * Created by Anton on 21.01.2017.
 * Заполнить двумерный массив числами от 0 до 100. Размер массива - 5х8. Найти сумму всех чисел в массиве.
 */
public class Task2 {
    public static void main(String[] args) {
        Task2 hw = new Task2();
        hw.array(5, 8);
    }

    private void array(int a, int b) {
        int[][] arr = new int[a][b];
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int num = (int) (0 + Math.random() * 100);
                arr[i][j] = num;
                sum += num;
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Сумма всех чисел массива: " + sum);
    }
}
