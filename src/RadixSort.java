import java.util.Arrays;

public class RadixSort {

    public static void radixSort(int[] a) {
        int maxValue = Arrays.stream(a).max().getAsInt();
        int[] t = new int[a.length];
        for (long n = 1; n <= maxValue; n *= 10) {
            int[] count = new int[10];
            for (int i : a) count[(i / (int) n) % 10]++;
            for (int i = 1; i < 10; i++) count[i] += count[i - 1];
            for (int i = a.length - 1; i >= 0; i--) t[--count[(a[i] / (int) n) % 10]] = a[i];
            System.arraycopy(t, 0, a, 0, a.length);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] { 1234567890, 734, Integer.MAX_VALUE, 0, 711 };
        radixSort(a);
        System.out.println(Arrays.toString(a));
    }
}
