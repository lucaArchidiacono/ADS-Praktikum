package Praktikum_12_Code;

public class QuickerSortServer extends QuickSortServer implements CommandExecutor, QuickSort {
    @Override
    public String execute(String command) {
        return ExecuteUtil.execute(this, command);
    }

    private void insertionSort(int[] a, int left, int right) {
        for (int k = left + 1; k < right + 1; k++) {
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
    public void quickSort(int[] arr, int left, int right) {
        if (right - left < ExecuteUtil.THRESHOLD) {
            insertionSort(arr, left, right);
        } else {
            int mid = partition (arr, left, right);
            quickSort(arr, left, mid -1);
            quickSort(arr, mid , right);
        }
    }
}
