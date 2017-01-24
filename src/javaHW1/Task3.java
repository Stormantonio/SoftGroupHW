package javaHW1;

import java.util.Arrays;

/**
 * Created by Anton on 22.01.2017.
 * Реализовать сортировку пузырьком от большего значения к меньшему
 * Реализовать сортировку вставкой и выборкой

 */
public class Task3 {
    public static void main(String[] args) {
        int[] arr = {45, 4, 2, 67, 9, 10};

        bubbleSortUp(arr);
        System.out.print("Сортировка массива пузырьком от меньшего к большему: ");
        arrayToString(arr);

        bubbleSortDown(arr);
        System.out.print("Сортировка массива пузырьком от большего к меньшему: ");
        arrayToString(arr);

        selectionSort(arr);
        System.out.print("Сортировка массива выборкой от меньшего к большему: ");
        arrayToString(arr);

        insertingSort(arr);
        System.out.print("Сортировка массива вставкой от меньшего к большему: ");
        arrayToString(arr);
    }

    // сортировка пузырьком от меньшенго к большему
    private static void bubbleSortUp(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    replace(arr, j, j + 1);
                }
            }
        }
    }

    // сортировка пузырьком от большего к меньшему
    private static void bubbleSortDown(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[j + 1]) {
                    replace(arr, j, j + 1);
                }
            }
        }
    }

    // сортировка выборкой
    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) { // сменить знак для обратной сортировки
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                replace(arr, i, minIndex);
            }
        }
    }

    // сортировка вставкой
    private static void insertingSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (arr[j] < arr[j - 1]) { // сменить знак для обратной сортировки
                    replace(arr, j, j - 1);
                }
            }
        }
    }

    private static void replace(int[] arr, int i, int j) {
        /*int temp = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = temp;*/
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void arrayToString(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
