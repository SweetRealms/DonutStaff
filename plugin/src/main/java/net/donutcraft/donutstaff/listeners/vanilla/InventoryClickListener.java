package net.donutcraft.donutstaff.listeners.vanilla;

import net.donutcraft.donutstaff.api.cache.SetCache;
import net.donutcraft.donutstaff.api.staffmode.StaffModeManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

public class InventoryClickListener implements Listener {

    @Inject private StaffModeManager staffModeManager;
    @Inject @Named("freeze-cache") private SetCache<UUID> freezeCache;

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();

        if (freezeCache.get().contains(player.getUniqueId()) || staffModeManager.isOnStaffMode(player)) {
            event.setCancelled(true);
        }
    }
}
