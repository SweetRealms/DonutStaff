package net.donucraft.cache;

import net.donutcraft.donutstaff.api.cache.Cache;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class StaffChatCache implements Cache<UUID> {

    private final Set<UUID> staffChatCache = new HashSet<>();

    @Override
    public Set<UUID> get() {
        return staffChatCache;
    }
}
