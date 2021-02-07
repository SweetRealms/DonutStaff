package net.donucraft.listeners;

import net.donutcraft.donutstaff.api.cache.Cache;
import net.donutcraft.donutstaff.api.staffmode.StaffModeHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

public class PlayerMoveListener implements Listener {

    @Inject private StaffModeHandler staffModeHandler;
    @Inject @Named("freeze-cache") private Cache<UUID> freezeCache;

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (freezeCache.exist(player.getUniqueId())) {
            player.teleport(event.getFrom());
            event.setCancelled(true);
        }
    }
}
