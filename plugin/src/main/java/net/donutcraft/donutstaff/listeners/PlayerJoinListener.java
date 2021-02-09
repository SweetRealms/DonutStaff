package net.donutcraft.donutstaff.listeners;

import net.donutcraft.donutstaff.api.cache.Cache;
import net.donutcraft.donutstaff.util.nms.NMSManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

public class PlayerJoinListener implements Listener {

    @Inject @Named("staff-mode-cache") private Cache<UUID> staffModeCache;
    @Inject private NMSManager nmsManager;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("donutstaff.seestaff")) {
            return;
        }

        for (UUID uuid : staffModeCache.get()) {
            nmsManager.getNMSHandler().hidePlayer(player, Bukkit.getPlayer(uuid));
        }
    }
}
