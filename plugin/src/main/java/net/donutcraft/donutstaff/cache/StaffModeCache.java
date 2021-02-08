package net.donutcraft.donutstaff.cache;

import net.donutcraft.donutstaff.api.cache.Cache;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class StaffModeCache implements Cache<UUID> {

    private final Set<UUID> staffModeCache = new HashSet<>();

    @Override
    public Set<UUID> get() {
        return staffModeCache;
    }
}
