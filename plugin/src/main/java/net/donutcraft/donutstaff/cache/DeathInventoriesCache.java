package net.donutcraft.donutstaff.cache;

import net.donutcraft.donutstaff.api.cache.MapCache;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DeathInventoriesCache implements MapCache<UUID, List<ItemStack>> {

    private final Map<UUID, List<ItemStack>> deathInventoriesCache = new HashMap<>();

    @Override
    public Map<UUID, List<ItemStack>> get() {
        return deathInventoriesCache;
    }
}
