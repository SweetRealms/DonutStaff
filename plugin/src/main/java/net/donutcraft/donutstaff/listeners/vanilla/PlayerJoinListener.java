package net.donutcraft.donutstaff.listeners.vanilla;

import net.donutcraft.donutstaff.api.cache.SetCache;
import net.donutcraft.donutstaff.api.staffmode.StaffModeManager;
import net.donutcraft.donutstaff.files.FileCreator;
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

    @Inject @Named("vanish-cache") private SetCache<UUID> vanishCache;
    @Inject @Named("messages") private FileCreator messages;
    @Inject private NMSManager nmsManager;
    @Inject private StaffModeManager staffModeManager;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (vanishCache.get().contains(player.getUniqueId())) {
            player.sendMessage(messages.getString("staff-mode.on-staff-join")
                    .replace("%prefix%", messages.getString("commons.global-prefix")));
            player.setAllowFlight(true);
            player.setFlying(true);
            staffModeManager.enableVanish(player);
            event.setJoinMessage(null);
            return;
        }

        if (player.hasPermission("donutstaff.seestaff")) {
            return;
        }

        vanishCache.get().forEach(uuid -> {
            Player staff = Bukkit.getPlayer(uuid);
            nmsManager.getNMSHandler().hidePlayer(player, staff);
        });
    }
}
