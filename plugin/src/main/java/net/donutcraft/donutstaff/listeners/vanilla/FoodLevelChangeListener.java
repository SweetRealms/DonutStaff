package net.donutcraft.donutstaff.listeners.vanilla;

import net.donutcraft.donutstaff.api.cache.SetCache;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

public class FoodLevelChangeListener implements Listener {

    @Inject @Named("staff-mode-cache") private SetCache<UUID> staffModeCache;

    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        if (staffModeCache.get().contains(player.getUniqueId())) {
            event.setCancelled(true);
        }
    }
}
