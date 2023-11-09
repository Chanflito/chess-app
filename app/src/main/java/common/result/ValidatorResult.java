package common.result;

import java.util.Optional;

public record ValidatorResult<K, V>(K key, Optional<V> value) implements Result<K, V> {
}
