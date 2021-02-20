package net.donutcraft.donutstaff.api.cache;

import java.util.Map;

public interface MapCache<T, K> {

    Map<T, K> get();
}
