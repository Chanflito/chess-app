package common.result;

import java.util.Optional;

public interface Result<K,V>{
    K key();
    Optional<V> value();
}
