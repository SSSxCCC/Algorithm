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
            int left = ni << 1, right = ni << 1 | 1;
            nodes[ni] = (left < nodes.length ? nodes[left] : 0) + (right < nodes.length ? nodes[right] : 0);
        }
    }

    public void update(int i, int delta) {
        for (int ni = firstLeaf + i; ni > 0; ni >>= 1) nodes[ni] += delta;
    }

    public int get(int i) {
        return nodes[firstLeaf + i];
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
        ZkwSegmentTree zst = new ZkwSegmentTree(1);
        System.out.println(zst.get(0));  // 0
        zst.update(0, 9);
        System.out.println(zst.get(0));  // 9

        zst = new ZkwSegmentTree(new int[] { 2, 1, 0, -3, 5 });
        System.out.println(zst.get(0, 1) + " " +  // 3 3 0 5 -2
                zst.get(0, 2) + " " +
                zst.get(0, 3) + " " +
                zst.get(0, 4) + " " +
                zst.get(1, 3));
    }

}
