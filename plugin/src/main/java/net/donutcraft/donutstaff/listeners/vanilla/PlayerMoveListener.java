package net.donutcraft.donutstaff.listeners.vanilla;

import net.donutcraft.donutstaff.api.cache.SetCache;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

public class PlayerMoveListener implements Listener {

    @Inject @Named("freeze-cache") private SetCache<UUID> freezeCache;

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (freezeCache.get().contains(player.getUniqueId())) {
            player.teleport(event.getFrom());
            event.setCancelled(true);
        }
    }
}
