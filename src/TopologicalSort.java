import java.util.*;

public class TopologicalSort {

    public static List<Integer> topologicalSort(Map<Integer, List<Integer>> graph) {
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (List<Integer> toList : graph.values()) {
            for (int v : toList) inDegree.put(v, inDegree.getOrDefault(v, 0) + 1);
        }
        List<Integer> open = new ArrayList<>();
        for (int v : graph.keySet()) if (!inDegree.containsKey(v)) open.add(v);
        List<Integer> sorted = new ArrayList<>();
        while (!open.isEmpty()) {
            int v = open.remove(open.size() - 1);
            sorted.add(v);
            for (int toV : graph.get(v)) {
                inDegree.put(toV, inDegree.get(toV) - 1);
                if (inDegree.get(toV) <= 0) open.add(toV);
            }
        }
        return sorted.size() == graph.size() ? sorted : null;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1));
        graph.put(1, Arrays.asList(2));
        graph.put(2, Arrays.asList(0));
        System.out.println(topologicalSort(graph));  // null

        graph = new HashMap<>();
        graph.put(0, Arrays.asList());
        System.out.println(topologicalSort(graph));  // [0]

        graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(2));
        graph.put(2, Arrays.asList(3, 4));
        graph.put(3, Arrays.asList(4));
        graph.put(4, Arrays.asList());
        System.out.println(topologicalSort(graph));  // [0, 1, 2, 3, 4]
    }
}
