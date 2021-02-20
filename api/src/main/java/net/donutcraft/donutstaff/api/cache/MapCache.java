package net.donutcraft.donutstaff.api.cache;

import java.util.Map;
import java.util.Optional;

public interface MapCache<K, V> {

    Map<K, V> get();

    default Optional<V> find(K k) {
        return Optional.ofNullable(get().get(k));
    }

}
