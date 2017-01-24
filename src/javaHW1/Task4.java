package javaHW1;

import java.util.Arrays;

/**
 * Created by Anton on 24.01.2017.
 */
public class Task4 {
    public static void main(String[] args) {
        Task4 task4 = new Task4();
        int[] arr1 = {1, 3, 3};
        int[] arr0 = {2, 6, 8, 10};

        System.out.println(Arrays.toString(Task4.merge(arr0, arr1)));

    }

    public static int[] merge(int[] arr0, int[] arr1) {
        int[] result = new int[arr1.length + arr0.length];

        System.out.println("arr0 - " + Arrays.toString(arr0));
        System.out.println("arr1 - " + Arrays.toString(arr1));

        int index0 = 0;
        int index1 = 0;

        while (index0 + index1 != result.length) {
            if (index0 < arr0.length && index1 < arr1.length) {
                if (arr0[index0] <= arr1[index1]) {
                    result[index0 + index1] = arr0[index0++];
                } else {
                    result[index0 + index1] = arr1[index1++];
                }
            } else if (index0 == arr0.length) {
                index0 = arr0.length - 1;
                System.arraycopy(arr1, index1, result, index1 + index0 + 1, arr1.length - index1);
                index1 = arr1.length - 1;
                index0 = result.length - index1; // added
            } else if (index1 == arr1.length) {
                index1 = arr1.length - 1;
                System.arraycopy(arr0, index0, result, index1 + index0 + 1, arr0.length - index0);
                index0 = arr0.length - 1;
                index1 = result.length - index0; // added
            }
            System.out.println("---" + Arrays.toString(result));
        }

        return result;
    }
}
