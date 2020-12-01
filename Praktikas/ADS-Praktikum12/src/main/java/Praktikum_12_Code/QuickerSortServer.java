package Praktikum_12_Code;

import java.util.Random;

public class QuickerSortServer extends QuickSortServer implements CommandExecutor {
    private static int THRESHOLD = 50;
    final static int ARRSIZE = 100000;

    @Override
    public String execute(String command) {
        THRESHOLD = Integer.parseInt(command);
        StringBuilder result = new StringBuilder();
        int[] arr = new int[ARRSIZE];
        Random randomNumberGenerator = new Random();

        for (int i = 0; i < ARRSIZE; i++) arr[i] = randomNumberGenerator.nextInt(ARRSIZE*5);
        long endTime, startTime = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        endTime = System.currentTimeMillis();
        for (int i = 0; i < ARRSIZE; i++) { result.append(arr[i] + "\n");}
        result.append("Laufzeit (ms): " + Double.toString(1.0 * (endTime - startTime)) + "\n");
        return result.toString();
    }

    private static void insertionSort(int[] a, int left, int right) {
        for (int k = left + 1; k < right +1; k++) {
            if (a[k] < a[k - left]) {
                int x = a[k];
                int i;
                for (i = k; ((i > 0) && (a[i-1] > x)); i--) {
                    a[i] = a[i - 1];
                }
                a[i] = x;
            }
        }
    }

    @Override
    public void quickSort(int[] arr, int left, int right) {
        if (right - left < THRESHOLD) {
            insertionSort(arr, left, right);
        } else {
            int mid = partition (arr, left, right);
            quickSort(arr, left, mid -1);
            quickSort(arr, mid , right);
        }
    }
}
