package net.donucraft.donutstaff.listeners;

import net.donutcraft.donutstaff.api.cache.Cache;
import net.donutcraft.donutstaff.api.staffmode.StaffModeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

public class BlockBreakListener implements Listener {

    @Inject private StaffModeManager staffModeManager;
    @Inject @Named("freeze-cache") private Cache<UUID> freezeCache;

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (freezeCache.exists(player.getUniqueId()) || staffModeManager.isOnStaffMode(player)) {
            event.setCancelled(true);
        }
    }
}
