package net.donutcraft.donutstaff.cache;

import net.donutcraft.donutstaff.api.cache.SetCache;

import java.util.HashSet;
import java.util.Set;

public class SetCacheImpl<T> implements SetCache<T> {

    private final Set<T> cacheSet = new HashSet<>();

	@Override
	public Set<T> get() {
		return cacheSet;
	}
}
