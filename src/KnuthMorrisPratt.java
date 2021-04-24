public class KnuthMorrisPratt {

    public final String pattern;
    private int[] kmp;

    public KnuthMorrisPratt(String pattern) {
        this.pattern = pattern;

        kmp = new int[pattern.length()];
        for (int i = 1, j = 0; i < kmp.length; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = kmp[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            kmp[i] = j;
        }
    }

    public int search(String string) {
        int n = string.length(), m = pattern.length();
        if (m == 0) return 0;
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && string.charAt(i) != pattern.charAt(j)) {
                j = kmp[j - 1];
            }
            if (string.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }
}
