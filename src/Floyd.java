public class Floyd {

    public static void shortestPath(int[][] graph) {
        int n = graph.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][k] < Integer.MAX_VALUE && graph[k][j] < Integer.MAX_VALUE)
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) {
        final int m = Integer.MAX_VALUE;
        int[][] graph = new int[][] { {m, m,-1, m, m, m},
                                      {m, m, m, m, m, m},
                                      {m, m, m,-2, m, m},
                                      {m, m, m, m, m, 3},
                                      {m, m, m, m, m, m},
                                      {m, 4, m, m, m, m} };
        shortestPath(graph);
        for (int[] tt : graph) {
            for (int t : tt) System.out.print((t >= 0 ? " " : "") + (t == m ? "m" : t) + " ");
            System.out.println();
        }
        // m  4 -1 -3  m  0
        // m  m  m  m  m  m
        // m  5  m -2  m  1
        // m  7  m  m  m  3
        // m  m  m  m  m  m
        // m  4  m  m  m  m
    }
}
