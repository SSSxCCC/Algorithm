import java.util.Arrays;

public class KnuthMorrisPratt {

    public final String pattern;
    private int[] next;

    public KnuthMorrisPratt(String pattern) {
        this.pattern = pattern;
        next = getNext(pattern);
    }

    private static int[] getNext(String pattern) {
        int[] next = new int[pattern.length()];
        if (next.length == 0) return next;
        next[0] = -1;
        for (int i = 0, j = -1; i < next.length - 1;) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                //next[++i] = ++j;
                if (pattern.charAt(++i) == pattern.charAt(++j)) {
                    next[i] = next[j];
                } else {
                    next[i] = j;
                }
            } else {
                j = next[j];
            }
        }
        return next;
    }

    public int search(String string) {
        int n = string.length(), m = pattern.length();
        if (m == 0) return 0;
        if (n < m) return -1;
        for (int i = 0, j = 0; i < n;) {
            if (j == -1 || string.charAt(i) == pattern.charAt(j)) {
                i++; j++;
            } else {
                j = next[j];
            }
            if (j == m) {
                return i - m;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        KnuthMorrisPratt kmp = new KnuthMorrisPratt("ababc");
        System.out.println(kmp.search("abababc"));  // 2
        System.out.println(Arrays.toString(kmp.next));  // [-1, 0, -1, 0, 2]

        System.out.println(new KnuthMorrisPratt("").search(""));  // 0
        System.out.println(new KnuthMorrisPratt("b").search("abc"));  // 1
    }
}
