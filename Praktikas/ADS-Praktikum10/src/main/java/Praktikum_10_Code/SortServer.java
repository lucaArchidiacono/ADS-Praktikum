package Praktikum_10_Code;


import java.lang.reflect.Array;
import java.util.Random;
import java.util.StringTokenizer;

public class SortServer implements CommandExecutor {
    enum SortType {
        BUBBLE,
        INSERTION,
        SELECTION
    }

    private static final int MAXRANGE = 1000000;

    @Override
    public String execute(String command) throws Exception {
        StringTokenizer args = new StringTokenizer(command);
        StringBuilder result = new StringBuilder();

        while (args.hasMoreTokens()) {
            SortType sortType = SortType.valueOf(args.nextToken());

            int elementLength = Integer.parseInt(args.nextToken());

            Integer[] elements = new Integer[elementLength];
            Integer[] arrayToSortCopy = new Integer[elementLength];

            for (int counter = 0; counter<elements.length; counter++){
                elements[counter] = (int) (Math.random() * MAXRANGE);
            }

            long end, start = System.currentTimeMillis();
            int count = 0;
            do {
                System.arraycopy(elements, 0, arrayToSortCopy, 0, elementLength);
                switch (sortType) {
                    case BUBBLE:
                        bubbleSort(arrayToSortCopy);
                    case INSERTION:
                        insertionSort(arrayToSortCopy);
                    case SELECTION:
                        selectionSort(arrayToSortCopy);
                }
                count++;
                end = System.currentTimeMillis();
            } while (end - start < 1000);
            if (!isSorted(arrayToSortCopy)) return "It's not sorted. Please check again.";
            result.append("\n");
            result.append("Record ").append(sortType).append(" ").append((double) (end- start) / count);
        }
        return result.toString();
    }

    private static <T extends Comparable<T>> void bubbleSort(T[] a) {
        for (int k = a.length-1; k > 0; k--) {
            boolean noSwap = true;
            for (int i = 0; i < k; i++) {
                if (a[i].compareTo(a[i + 1]) > 0) {
                    swap (a, i, i + 1);
                    noSwap = false;
                }
            }
            if (noSwap) break;
        }
    }

    private static <T extends Comparable<T>> void insertionSort(T[] a){
        for (int k = 1; k < a.length; k++)
            if (a[k].compareTo(a[k-1]) < 0) {
                T x = a[k];
                int i;
                for (i = k; ((i > 0) && (a[i-1].compareTo(x) > 0));i--)
                    a[i] = a[i-1];
                a[i] = x;
            }
    }

    private static <T extends Comparable<T>> void selectionSort(T[] a){
        for (int k = 0; k < a.length; k++){
            int min = k;
            for (int i = k+1; i < a.length; i ++) {
                if (a[i].compareTo(a[min]) < 0) min = i;
            }
            if (min != k) swap (a, min, k);
        }
    }

    private <T extends Comparable<T>> boolean isSorted(T[] a) {
        for (int i = 0; i < a.length-1; i ++)
            if (a[i].compareTo(a[i+1]) > 0) return false;
        return true;
    }

    private static <K> void swap(K[] k, int i, int j) {
        K h = k[i]; k[i] = k[j]; k[j] = h;
    }
}
