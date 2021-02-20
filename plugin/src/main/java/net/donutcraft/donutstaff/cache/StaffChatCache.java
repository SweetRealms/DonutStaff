package net.donutcraft.donutstaff.cache;

import net.donutcraft.donutstaff.api.cache.SetCache;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class StaffChatCache implements SetCache<UUID> {

    private final Set<UUID> staffChatCache = new HashSet<>();

    @Override
    public Set<UUID> get() {
        return staffChatCache;
    }
}
