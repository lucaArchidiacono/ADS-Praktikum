package Praktikum_12_Code;

import java.util.Random;

public class QuickSortServer implements QuickSort {

    public void quickSort(int[] a){
        quickSort(a, 0, a.length-1);
    }

    public void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = partition (arr, left, right);
            quickSort(arr, left, mid -1);
            quickSort(arr, mid , right);
        }
    }

    public int partition (int[] arr, int left, int right) {
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

    public void swap(int[] arr, int i, int j) {
        int swapElement;

        swapElement = arr[i];
        arr[i] = arr[j];
        arr[j] = swapElement;
    }
}