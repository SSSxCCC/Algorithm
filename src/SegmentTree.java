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

    public void update(int i, int delta) {
        update(i, delta, 0, 0, size - 1);
    }

    private void update(int i, int delta, int ni, int left, int right) {
        if (left > i || right < i) return;
        nodes[ni] += delta;
        if (left < right) {
            int middle = (left + right) / 2;
            update(i, delta, ni * 2 + 1, left, middle);
            update(i, delta, ni * 2 + 2, middle + 1, right);
        }
    }

    public int get(int i) {
        return get(i, i);
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
        st.update(0, 2);
        st.update(1, 1);
        st.update(3, -3);
        st.update(4, 5);
        for (int i = 0; i < 5; i++) {
            System.out.print(st.get(i) + " ");  // 2 1 0 -3 5
        }
        System.out.println();
        System.out.println(st.get(0, 1) + " " +  // 3 3 0 5 -2
                st.get(0, 2) + " " +
                st.get(0, 3) + " " +
                st.get(0, 4) + " " +
                st.get(1, 3));
    }
}
