import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache<K, V> extends LinkedHashMap {

    public static void main(String[] args) {
        LruCache<Integer, Integer> cache = new LruCache<>(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache);
        cache.get(1);
        System.out.println(cache);
        cache.put(3, 3);
        System.out.println(cache);
    }

    private int cacheSize;

    public LruCache(int cacheSize) {
        super(16, 0.75f, true);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry entry) {
        return size() > cacheSize;
    }
}
