import java.util.Arrays;

public class QuickSort {

    public static void quickSort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    public static void quickSort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }

        int m = left;
        for (int l = left, r = right; l < r;) {
            for (; l < r; r--) {
                if (a[r] < a[m]) {
                    swap(a, r, m);
                    m = r;
                    break;
                }
            }

            for (; l < r; l++) {
                if (a[l] > a[m]) {
                    swap(a, l, m);
                    m = l;
                    break;
                }
            }
        }

        quickSort(a, left, m - 1);
        quickSort(a, m + 1, right);
    }

    public static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = new int[] { 3, 4, 2, 1, 5 };
        quickSort(a);
        System.out.println(Arrays.toString(a));
    }
}
