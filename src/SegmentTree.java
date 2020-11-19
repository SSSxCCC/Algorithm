/**
 *              0
 *          /       \
 *       1             2
 *    /    \        /    \
 *   3      4      5      6
 *  / \    / \    / \    / \
 * 7  8   9  10  11 12  13 14
 */
public class SegmentTree {

    private int[] nodes;
    private int size;

    public SegmentTree(int size) {
        this.size = size;
        nodes = new int[size * 4];
    }

    public void add(int value) {
        add(value, 0, 0, size - 1);
    }

    private void add(int value, int ni, int left, int right) {
        if (left > value || right < value) {
            return;
        }

        nodes[ni]++;

        if (left < right) {
            int middle = (left + right) / 2;
            add(value, ni * 2 + 1, left, middle);
            add(value, ni * 2 + 2, middle + 1, right);
        }
    }

    public int get(int value) {
        return get(value, value);
    }

    public int get(int min, int max) {
        return get(min, max, 0, 0, size - 1);
    }

    private int get(int min, int max, int ni, int left, int right) {
        if (left >= min && right <= max) {
            return nodes[ni];
        } else if (right < min || left > max) {
            return 0;
        } else { // left < right
            int middle = (left + right) / 2;
            return get(min, max, ni * 2 + 1, left, middle) +
                    get(min, max, ni * 2 + 2, middle + 1, right);
        }
    }

    public static void main(String[] args) {
        SegmentTree st = new SegmentTree(5);
        int[] a = new int[] { 0, 0, 1, 3 };
        for (int x : a) {
            st.add(x);
        }
        for (int i = 0; i < 5; i++) {
            System.out.print(st.get(i) + " ");  // 2 1 0 1 0
        }
        System.out.println();
        System.out.println(st.get(0, 1) + " " +  // 3 3 4 4 2
                st.get(0, 2) + " " +
                st.get(0, 3) + " " +
                st.get(0, 4) + " " +
                st.get(1, 3));
    }
}
