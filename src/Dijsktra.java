import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijsktra {

    private static class Vertex implements Comparable<Vertex> {
        public final int index;
        public final int length;

        public Vertex(int index, int length) {
            this.index = index;
            this.length = length;
        }

        @Override
        public int compareTo(Vertex that) {
            return Integer.compare(this.length, that.length);
        }
    }

    public static int shortestPath(int[][] graph, int start, int end, int[] path, int[] length) {
        int vertexSize = graph.length;
        for (int i = 0; i < vertexSize; i++) {
            path[i] = -1;
            length[i] = -1;
        }
        boolean[] closed = new boolean[vertexSize];
        PriorityQueue<Vertex> open = new PriorityQueue<>();
        open.offer(new Vertex(start, 0));
        path[start] = start;
        length[start] = 0;
        while (!open.isEmpty()) {
            Vertex vertex = open.poll();
            if (closed[vertex.index]) continue;
            closed[vertex.index] = true;
            if (vertex.index == end) return vertex.length;
            for (int i = 0; i < vertexSize; i++) {
                if (vertex.index == i || graph[vertex.index][i] < 0) continue;
                int len = vertex.length + graph[vertex.index][i];
                if (length[i] < 0 || len < length[i]) {
                    open.offer(new Vertex(i, len));
                    path[i] = vertex.index;
                    length[i] = len;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] graph;
        int[] path, length;

        graph = new int[][] { { -1,  1,  4 },
                              { -1, -1,  2 },
                              { -1, -1, -1 } };
        path = new int[graph.length];
        length = new int[graph.length];
        System.out.println(shortestPath(graph, 0, 2, path, length));  // 3
        System.out.println(Arrays.toString(path));  // 0, 0, 1
        System.out.println(Arrays.toString(length));  // 0, 1, 3
        System.out.println("-----------------------------------");

        graph = new int[][] { { -1,  1,  4, -1 },
                              { -1, -1,  2, -1 },
                              { -1, -1, -1, -1 },
                              {  1,  1,  1, -1 } };
        path = new int[graph.length];
        length = new int[graph.length];
        System.out.println(shortestPath(graph, 0, 3, path, length));  // -1
        System.out.println(Arrays.toString(path));  // 0, 0, 1, -1
        System.out.println(Arrays.toString(length));  // 0, 1, 3, -1
    }

}
