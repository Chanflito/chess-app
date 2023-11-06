package common.result;

import java.util.Optional;

public record MoveResult<K, V>(K key, Optional<V> value) implements Result<K, V> {
}
