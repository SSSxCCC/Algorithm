import java.util.*;

public class Trie {

    private static class Node {
        String word = null;
        Map<Character, Node> children = new HashMap<>();
    }

    private Node root = new Node();

    public void add(String s) {
        Node current = root;
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            if (!current.children.containsKey(c)) {
                current.children.put(c, new Node());
            }
            current = current.children.get(c);
        }
        current.word = s;
    }

    public boolean contains(String s) {
        Node current = root;
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            if (current.children.containsKey(c)) {
                current = current.children.get(c);
            } else {
                return false;
            }
        }
        return current.word != null;
    }

    public List<String> getAll() {
        List<String> all = new ArrayList<>();
        List<Node> stack = new ArrayList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node current = stack.remove(stack.size() - 1);
            stack.addAll(current.children.values());
            if (current.word != null) all.add(current.word);
        }
        return all;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("a");
        trie.add("abc");
        trie.add("cxz");
        System.out.println(trie.contains("a"));
        System.out.println(trie.contains("ab"));
        System.out.println(trie.contains("abc"));
        System.out.println(trie.getAll());
    }
}
