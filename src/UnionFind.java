public class UnionFind {

    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        rank = new int[n];
    }

    public int find(int i) {
        return parent[i] == i ? i : (parent[i] = find(parent[i]));
    }

    public void union(int i, int j) {
        int ri = find(i), rj = find(j);
        if (rank[ri] > rank[rj]) parent[rj] = ri;
        else parent[ri] = rj;
        if (rank[ri] == rank[rj] && ri != rj) rank[rj]++;
    }

    public static void main(String[] args) {
        // 0 3 6 9   1 4 7   2 5 8
        UnionFind uf = new UnionFind(10);
        uf.union(0, 3); uf.union(9, 3); uf.union(3, 6);
        uf.union(7, 4); uf.union(1, 4);
        uf.union(2, 5); uf.union(5, 8);
        for (int i = 0; i < 10; i++) {
            System.out.println("find(" + i + ") = " + uf.find(i));
        }
    }
}
