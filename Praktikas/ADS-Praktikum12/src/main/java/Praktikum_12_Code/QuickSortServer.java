package Praktikum_12_Code;

import java.util.Random;

public class QuickSortServer implements CommandExecutor {
    final static int ARRSIZE = 100000;

    public String execute(String arg) throws Exception {
        StringBuffer result = new StringBuffer();
        int[] arr = new int[ARRSIZE];
        Random randomNumberGenerator = new Random();

        for (int i = 0; i < ARRSIZE; i++) arr[i] = randomNumberGenerator.nextInt(ARRSIZE*5);
        long endTime, startTime = System.currentTimeMillis();
        quickSort(arr);
        endTime = System.currentTimeMillis();
        for (int i = 0; i < ARRSIZE; i++) { result.append(arr[i] + "\n");}
        result.append("Laufzeit (ms): " + Double.toString(1.0 * (endTime - startTime)) + "\n");
        return result.toString();
    }


    private static void quickSort(int[] a){
        quickSort(a, 0, a.length-1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = partition (arr, left, right);
            quickSort(arr, left, mid -1);
            quickSort(arr, mid , right);
        }
    }

    private static int partition (int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];
        while (left <= right) {
            while (arr[left] < pivot) { left++; }
            while (arr[right] > pivot) { right--; }
            if (left <= right) {
                swap(arr,left,right);
                left++;
                right--;
            }
        }
        return left;
    }

    private static void swap(int[] arr, int i, int j) {
        int swapElement;

        swapElement = arr[i];
        arr[i] = arr[j];
        arr[j] = swapElement;
    }

}