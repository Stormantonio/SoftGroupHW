package javaHW1;

import java.util.Arrays;

/**
 * Created by Anton on 22.01.2017.
 */
public class DeleteClass {
    public static void main(String[] args) {
        int[] arr = {45, 4, 2, 67, 9, 10};

        for (int i = arr.length - 1; i > 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[i + 1]) {

                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
