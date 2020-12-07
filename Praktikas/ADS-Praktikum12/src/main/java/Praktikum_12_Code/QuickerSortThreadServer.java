package Praktikum_12_Code;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class QuickerSortThreadServer extends RecursiveAction implements CommandExecutor, QuickSort {
    private final int SPLIT_THRESHOLD = 1000;

    private int[] arr;
    private int left;
    private int right;

    public QuickerSortThreadServer() {}
    public QuickerSortThreadServer(int[] arr, int left, int right) {
        this.arr = arr;
        this.left = left;
        this.right = right;
    }

    @Override
    public String execute(String command) {
        return ExecuteUtil.execute(this, command);
    }

    @Override
    public void compute() {
        if (left < right) {
            System.out.println("" + left + " " + right);
            int l = partition(arr, left, right);
            ForkJoinTask t1, t2;

            if (l - left > SPLIT_THRESHOLD && right - l > SPLIT_THRESHOLD) {
                t1 = new QuickerSortThreadServer(arr, left, l - 1);
                t2 = new QuickerSortThreadServer(arr, l, right);
                invokeAll(t1, t2);
            } else {
                quickSort(arr, left, l - 1);
                quickSort(arr, l, right);
            }
        }
    }

    @Override
    public void quickerSort(int[] a) {}

    @Override
    public void quickSort(int[] arr, int left, int right) {
        if (right - left < ExecuteUtil.THRESHOLD) {
            if (left < right) {
                insertionSort(arr, left, right);
            }
        } else {
            int l = partition(arr, left, right);
            quickSort(arr, left, l - 1);
            quickSort(arr, l, right);
        }
    }

    private void insertionSort(int[] a, int l, int r) {
        if (l == r) {
            return;
        }
        for (int k = l + 1; k < r + 1; k++) {
            if (a[k] < a[k - 1]) {
                int x = a[k];
                int i;
                for (i = k; ((i > 0) && (a[i - 1] > x)); i--) {
                    a[i] = a[i - 1];
                }
                a[i] = x;
            }
        }
    }

    @Override
    public int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];
        while (left <= right) {
            while (arr[left] < pivot) {
                left++;
            }
            while (arr[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    @Override
    public void swap(int[] arr, int i, int j) {
        int swapElement = arr[i];
        arr[i] = arr[j];
        arr[j] = swapElement;
    }
}
