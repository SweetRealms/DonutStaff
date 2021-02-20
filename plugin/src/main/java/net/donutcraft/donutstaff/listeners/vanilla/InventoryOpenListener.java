package net.donutcraft.donutstaff.listeners.vanilla;

import net.donutcraft.donutstaff.api.cache.SetCache;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

public class InventoryOpenListener implements Listener {

    @Inject @Named("freeze-cache") private SetCache<UUID> freezeCache;

    @EventHandler
    public void onInventoryOpenEvent(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();

        if (freezeCache.get().contains(player.getUniqueId())) {
            event.setCancelled(true);
        }

    }

}