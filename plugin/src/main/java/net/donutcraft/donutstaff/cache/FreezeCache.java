package net.donutcraft.donutstaff.cache;

import net.donutcraft.donutstaff.api.cache.Cache;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FreezeCache implements Cache<UUID> {

    private final Set<UUID> freezeCache = new HashSet<>();

    @Override
    public Set<UUID> get() {
        return freezeCache;
    }
}
