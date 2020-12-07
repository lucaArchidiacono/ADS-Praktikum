package Praktikum_12_Code;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ExecuteUtil {
    static int THRESHOLD = 50;
    final static int ARRSIZE = 100000;

    public static String execute(QuickSort quickSort, String command){
        THRESHOLD = Integer.parseInt(command);
        StringBuilder result = new StringBuilder();
        int[] a = new int[ARRSIZE];
        int[] b = new int[ARRSIZE];
        randomData(a);
        int threads;
        ForkJoinPool forkJoinPool;

        long endTime, startTime = System.currentTimeMillis();
        int rep = 0;
        do {
            System.arraycopy(a, 0, b, 0, a.length);
            if (quickSort instanceof QuickerSortServer) {
                quickSort.quickerSort(b);
            }
            if (quickSort instanceof QuickerSortThreadServer) {
                threads = 1;
                forkJoinPool = new ForkJoinPool(threads);
                QuickerSortThreadServer root = new QuickerSortThreadServer(b, 0, b.length-1);
                forkJoinPool.invoke(root);
            }
            else {
                quickSort.quickSort(b, 0, b.length - 1);
            }
            rep++;
            endTime = System.currentTimeMillis();
        } while (endTime - startTime < 2000);
        if (!isSorted(b)) {
            return "ERROR \n";
        }
        result.append("Laufzeit (ms): ").append(1.0 * (endTime - startTime) / rep).append("\n");

        return result.toString();
    }

    public static void randomData(int[] arr) {
        Random randomNumberGenerator = new Random();
        for (int i = 0; i < ARRSIZE; i++) arr[i] = randomNumberGenerator.nextInt(ARRSIZE*5);
    }

    public static boolean isSorted(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
