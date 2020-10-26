
public class SegmentTree {

    public static void main(String[] args) {
        SegmentTree segmentTree = new SegmentTree(5);
        int[] a = new int[] { 0, 0, 1, 3 };
        for (int x : a) {
            segmentTree.add(x);
        }
        for (int i = 0; i < 5; i++) {
            System.out.print(segmentTree.get(i) + " ");  // 2 1 0 1 0
        }
        System.out.println();
        System.out.println(segmentTree.get(0, 1) + " " +  // 3 3 4 4 2
                segmentTree.get(0, 2) + " " +
                segmentTree.get(0, 3) + " " +
                segmentTree.get(0, 4) + " " +
                segmentTree.get(1, 3));
    }

    private int[] nodes;
    private int size;

    public SegmentTree(int size) {
        this.size = size;
        nodes = new int[size * 2 - 1];
    }

    public void add(int value) {
        add(value, 0, 0, size - 1);
    }

    private void add(int value, int ni, int left, int right) {
        if (ni >= nodes.length || value < left || value > right) {
            return;
        }

        nodes[ni]++;
        int middle = (left + right) / 2;
        add(value, ni * 2 + 1, left, middle);
        add(value, ni * 2 + 2, middle + 1, right);
    }

    public int get(int value) {
        return get(value, value);
    }

    public int get(int min, int max) {
        return get(min, max, 0, 0, size - 1);
    }

    private int get(int min, int max, int ni, int left, int right) {
        if (min <= left && max >= right) {
            return nodes[ni];
        } else if (min > right || max < left) {
            return 0;
        }

        int middle = (left + right) / 2;
        return get(min, max, ni * 2 + 1, left, middle) +
                get(min, max, ni * 2 + 2, middle + 1, right);
    }
}
