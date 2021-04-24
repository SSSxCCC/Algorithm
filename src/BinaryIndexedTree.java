/**
 * index    prefixSum
 * (1) 1    1
 * (2) 10   2
 * (3) 11   3+2
 * (4) 100  4
 * (5) 101  5+4
 * (6) 110  6+4
 * (7) 111  7+6+4
 * (8) 1000 8
 * (9) 1001 9+8
 * (10)1010 10+8
 * (11)1011 11+10+8
 * (12)1100 12+8
 * (13)1101 13+12+8
 * (14)1110 14+12+8
 * (15)1111 15+14+12+8
 */
public class BinaryIndexedTree {

    private int[] a;
    private int[] tree;

    public BinaryIndexedTree(int size) {
        a = new int[size];
        tree = new int[size + 1];
    }

    public BinaryIndexedTree(int[] a) {
        this.a = a;
        tree = new int[a.length + 1];
        System.arraycopy(a, 0, tree, 1, a.length);
        for (int i = 1; i < tree.length; i++) {
            int j = i + (i & -i);
            if (j < tree.length) {
                tree[j] += tree[i];
            }
        }
    }

    public void update(int i, int delta) {
        a[i++] += delta;
        while (i < tree.length) {
            tree[i] += delta;
            i = i + (i & -i);
        }
    }

    public int prefixSum(int i) {
        i += 1;
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i = i - (i & -i);
        }
        return sum;
    }

    public static void main(String[] args) {
        BinaryIndexedTree bit = new BinaryIndexedTree(3);
        bit.update(0, 3);
        bit.update(1, -2);
        bit.update(2, 1);
        System.out.println(bit.prefixSum(0) + " " + bit.prefixSum(1) + " " + bit.prefixSum(2));  // 3 1 2
        bit = new BinaryIndexedTree(new int[] { 3, -2, 1 });
        System.out.println(bit.prefixSum(0) + " " + bit.prefixSum(1) + " " + bit.prefixSum(2));  // 3 1 2
        bit.update(1, -5);
        System.out.println(bit.prefixSum(0) + " " + bit.prefixSum(1) + " " + bit.prefixSum(2));  // 3 -4 -3
    }

}
