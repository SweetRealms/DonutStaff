package net.donutcraft.donutstaff.listeners.vanilla;

import net.donutcraft.donutstaff.api.cache.Cache;
import net.donutcraft.donutstaff.util.nms.NMSManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

public class PlayerQuitListener implements Listener {

    @Inject @Named("vanish-cache") private Cache<UUID> vanishCache;
    @Inject private NMSManager nmsManager;

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        vanishCache.get().forEach(uuid -> {
            Player staff = Bukkit.getPlayer(uuid);
            nmsManager.getNMSHandler().showPlayer(player, staff);
        });
    }
}
