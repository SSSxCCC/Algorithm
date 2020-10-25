import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] a = new int[] { 3, 4, 2, 1, 5 };
        mergeSort(a);
        for (int x : a) {
            System.out.println(x + " ");
        }
    }

    public static void mergeSort(int[] a) {
        int[] b = Arrays.copyOf(a, a.length);
        mergeSort(a, 0, a.length - 1, b);
        System.arraycopy(b, 0, a, 0, a.length);
    }

    public static void mergeSort(int[] a, int left, int right, int[] b) {
        if (left > right) {
            return;
        }

        int middle = (left + right) / 2;
        mergeSort(a, left, middle, b);
        mergeSort(a, middle + 1, right, b);
        merge(a, left, middle, right, b);
    }

    public static void merge(int[] a, int left, int middle, int right, int[] b) {
        for (int i = left, l = left, r = middle + 1; i <= right; i++) {
            if (l > middle) {
                b[i] = a[r];
                r++;
            } else if (r > right) {
                b[i] = a[l];
                l++;
            } else if (a[]) {

            }

        }
    }
}
