package result;

public class MoveResult<K,V> implements Result<K,V>{
    private final K key;
    private final V value;

    public MoveResult(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }
}
