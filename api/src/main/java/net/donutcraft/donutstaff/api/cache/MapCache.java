package net.donutcraft.donutstaff.api.cache;

import java.util.Map;

public interface MapCache<K, V> {

    Map<K, V> get();
}
