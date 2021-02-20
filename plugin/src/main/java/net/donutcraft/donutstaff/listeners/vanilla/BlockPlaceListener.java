package net.donutcraft.donutstaff.listeners.vanilla;

import net.donutcraft.donutstaff.api.cache.SetCache;
import net.donutcraft.donutstaff.api.staffmode.StaffModeManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

public class BlockPlaceListener implements Listener {

    @Inject
    private StaffModeManager staffModeManager;
    @Inject @Named("freeze-cache") private SetCache<UUID> freezeCache;

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (freezeCache.get().contains(player.getUniqueId()) || staffModeManager.isOnStaffMode(player)) {
            event.setCancelled(true);
        }
    }
}
