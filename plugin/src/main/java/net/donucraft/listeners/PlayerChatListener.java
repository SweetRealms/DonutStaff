package net.donucraft.listeners;

import net.donucraft.files.FileCreator;
import net.donutcraft.donutstaff.api.cache.Cache;
import net.donutcraft.donutstaff.api.staffmode.StaffModeHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

public class PlayerChatListener implements Listener {

    @Inject private StaffModeHandler staffModeHandler;
    @Inject @Named("messages") private FileCreator messages;
    @Inject @Named("staff-chat-cache") private Cache<UUID> staffChatCache;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (staffChatCache.exists(player.getUniqueId())) {
            for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
                if (staff.hasPermission("donutcraft.staffchat.receive")) {
                    staff.sendMessage(messages.getString("staff-mode.staff-chat.prefix")
                            .replace("%player_name%", player.getName()) + message);
                }
            }
            event.setMessage(null);
            event.setCancelled(true);
        }

    }
}
