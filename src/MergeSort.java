import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] a) {
        int[] t = new int[a.length];
        mergeSort(a, 0, a.length - 1, t);
    }

    public static void mergeSort(int[] a, int left, int right, int[] t) {
        if (left >= right) {
            return;
        }

        int middle = (left + right) / 2;
        mergeSort(a, left, middle, t);
        mergeSort(a, middle + 1, right, t);
        merge(a, left, middle, right, t);
    }

    public static void merge(int[] a, int left, int middle, int right, int[] t) {
        for (int i = left, l = left, r = middle + 1; i <= right; i++) {
            if (l > middle) {
                t[i] = a[r++];
            } else if (r > right) {
                t[i] = a[l++];
            } else if (a[l] <= a[r]) {
                t[i] = a[l++];
            } else { // a[l] > a[r]
                t[i] = a[r++];
            }
        }

        System.arraycopy(t, left, a, left, right - left + 1);
    }

    public static void main(String[] args) {
        int[] a = new int[] { 3, 4, 2, 1, 5 };
        mergeSort(a);
        System.out.println(Arrays.toString(a));
    }
}
