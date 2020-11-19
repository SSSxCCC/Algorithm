/**
 *              1
 *          /       \
 *       2             3
 *    /    \        /    \
 *   4      5      6      7
 *  / \    / \    / \    / \
 * 8  9  10  11  12 13  14 15
 */
public class ZkwSegmentTree {

    private int[] nodes;
    private int size;
    private int firstLeaf;

    public ZkwSegmentTree(int size) {
        this.size = size;
        for (firstLeaf = 1; firstLeaf < size; firstLeaf <<= 1);
        nodes = new int[firstLeaf + size];
    }

    public ZkwSegmentTree(int[] a) {
        size = a.length;
        for (firstLeaf = 1; firstLeaf < size; firstLeaf <<= 1);
        nodes = new int[firstLeaf + size];
        System.arraycopy(a, 0, nodes, firstLeaf, size);
        for (int ni = firstLeaf - 1; ni > 0; ni--) {
            int left = ni << 1, right = (ni << 1) | 1;
            nodes[ni] = (left < nodes.length ? nodes[left] : 0) + (right < nodes.length ? nodes[right] : 0);
        }
    }

    public void add(int value) {
        for (int i = firstLeaf + value; i > 0; i >>= 1) nodes[i]++;
    }

    public int get(int value) {
        return nodes[firstLeaf + value];
    }

    public int get(int min, int max) {
        int sum = 0, left = firstLeaf + min, right = firstLeaf + max;
        for (; left < right; left >>= 1, right >>= 1) {
            if ((left & 1) == 1) sum += nodes[left++];
            if ((right & 1) == 0) sum += nodes[right--];
        }
        if (left == right) sum += nodes[left];
        return sum;
    }

    public static void main(String[] args) {
        ZkwSegmentTree zst = new ZkwSegmentTree(5);
        int[] a = new int[] { 0, 0, 1, 3 };
        for (int x : a) {
            zst.add(x);
        }
        for (int i = 0; i < 5; i++) {
            System.out.print(zst.get(i) + " ");  // 2 1 0 1 0
        }
        System.out.println();
        System.out.println(zst.get(0, 1) + " " +  // 3 3 4 4 2
                zst.get(0, 2) + " " +
                zst.get(0, 3) + " " +
                zst.get(0, 4) + " " +
                zst.get(1, 3));

        zst = new ZkwSegmentTree(new int[] { 2, 1, 0, 1, 0 });
        System.out.println(zst.get(0, 1) + " " +  // 3 3 4 4 2
                zst.get(0, 2) + " " +
                zst.get(0, 3) + " " +
                zst.get(0, 4) + " " +
                zst.get(1, 3));
    }

}
