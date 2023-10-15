package result;

public interface Result<K,V>{
    K getKey();
    V getValue();
}
