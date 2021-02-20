package net.donutcraft.donutstaff.cache;

import net.donutcraft.donutstaff.api.cache.MapCache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleMapCache<K, V> implements MapCache<K, V> {

    private final Map<K, V> cacheMap = new ConcurrentHashMap<>();

    @Override
    public Map<K, V> get() {
        return cacheMap;
    }
}
