package net.donutcraft.donutstaff.listeners.vanilla;

import net.donutcraft.donutstaff.api.cache.SetCache;
import net.donutcraft.donutstaff.api.staffmode.StaffModeManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

public class PlayerDamageListener implements Listener {

    @Inject @Named("freeze-cache") private SetCache<UUID> freezeCache;
    @Inject private StaffModeManager staffModeManager;

    @EventHandler
    public void onPlayerDamageEvent(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();
        if (freezeCache.get().contains(player.getUniqueId()) || staffModeManager.isOnStaffMode(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() == null) {
            return;
        }
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        if (!(event.getDamager() instanceof Player)) {
           return;
        }
        Player player = (Player) event.getDamager();
        Player player1 = (Player) event.getEntity();

        if (staffModeManager.isOnStaffMode(player)) {
            return;
        }

        if (freezeCache.get().contains(player.getUniqueId())) {
            event.setCancelled(true);
            return;
        }
        if (freezeCache.get().contains(player1.getUniqueId()) || staffModeManager.isOnStaffMode(player1)) {
            event.setCancelled(true);
        }
    }
}
