package Praktikum_12_Code;

public interface QuickSort {
    void quickerSort(int[] a);
    void quickSort(int[] arr, int left, int right);
    int partition (int[] arr, int left, int right);
    void swap(int[] arr, int i, int j);
}
